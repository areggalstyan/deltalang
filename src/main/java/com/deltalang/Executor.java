package com.deltalang;

import com.deltalang.error.ErrorHandler;
import com.deltalang.error.Message;
import com.deltalang.error.OperationException;
import com.deltalang.expression.*;
import com.deltalang.io.InputStream;
import com.deltalang.io.OutputStream;
import com.deltalang.io.Sequence;
import com.deltalang.object.*;
import com.deltalang.statement.ExpressionStatement;
import com.deltalang.statement.Statement;
import com.deltalang.statement.StatementVisitor;
import com.deltalang.statement.function.DefinitionStatement;
import com.deltalang.statement.function.ReturnStatement;
import com.deltalang.statement.logic.BlockStatement;
import com.deltalang.statement.logic.ForStatement;
import com.deltalang.statement.logic.IfElseStatement;
import com.deltalang.statement.logic.WhileStatement;
import com.deltalang.statement.variable.AssignmentStatement;
import com.deltalang.statement.variable.DeclarationStatement;
import com.deltalang.token.TokenPosition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Executor implements StatementVisitor, ExpressionVisitor<RuntimeObject> {
    ErrorHandler handler;
    OutputStream output;
    InputStream input;
    Environment environment;

    public Executor(ErrorHandler handler, OutputStream output, InputStream input) {
        this.handler = handler;
        this.output = output;
        this.input = input;
        environment = new Environment(handler);

        environment.declareNativeFunction("print", this::print, "x");
        environment.declareNativeFunction("input", this::input);
        environment.declareNativeFunction("parse", this::parse, "string");
    }

    public void execute(Sequence<Statement> statements) {
        while (statements.hasNext() && environment.isAbsent(FunctionObject.RETURN)) {
            statements.consume().accept(this);
        }
    }

    public RuntimeObject print(Map<String, RuntimeObject> arguments) {
        output.write(arguments.get("x").asString().value().translateEscapes());
        return null;
    }

    public RuntimeObject input(Map<String , RuntimeObject> arguments) {
        try {
            return new StringObject(input.read());
        } catch (NoSuchElementException exception) {
            return null;
        }
    }

    public RuntimeObject parse(Map<String, RuntimeObject> arguments) {
        var string = arguments.get("string").asString();

        try {
            return new NumberObject(Double.parseDouble(string.value()));
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    @Override
    public RuntimeObject visit(BinaryExpression expression) {
        var operator = expression.operator();
        var leftExpression = expression.left();
        var rightExpression = expression.right();

        if (leftExpression == null || rightExpression == null) {
            panic(operator.position(), Message.OPERAND_MISSING, operator.type());
        }

        var left = leftExpression.accept(this);
        var right = rightExpression.accept(this);

        if (left == null || right == null) {
            panic(operator.position(), Message.OPERAND_MISSING, operator.type());
        }

        try {
            return switch (operator.type()) {
                case PLUS -> left.sum(right);
                case MINUS -> left.subtract(right);
                case MULTIPLY -> left.multiply(right);
                case DIVIDE -> left.divide(right);
                case MODULO -> left.modulo(right);
                case GREATER -> new BoolObject(left.compare(right) > 0);
                case GREATER_EQUAL -> new BoolObject(left.compare(right) >= 0);
                case LESS -> new BoolObject(left.compare(right) < 0);
                case LESS_EQUAL -> new BoolObject(left.compare(right) <= 0);
                case EQUAL_EQUAL -> new BoolObject(left.compare(right) == 0);
                case BANG_EQUAL -> new BoolObject(left.compare(right) != 0);
                default -> null;
            };
        } catch (OperationException exception) {
            panic(operator.position(), exception.message(), left.type(), right.type());
        }

        return null;
    }

    @Override
    public RuntimeObject visit(UnaryExpression expression) {
        var operandExpression = expression.operand();
        var operator = expression.operator();
        var position = operator.position();

        if (operandExpression == null) {
            panic(position, Message.OPERAND_MISSING, operator.type());
        }

        var operand = operandExpression.accept(this);

        if (operand == null) {
            panic(position, Message.OPERAND_MISSING, operator.type());
        }

        try {
            return switch (operator.type()) {
                case BANG -> operand.negate();
                case MINUS -> operand.inverse();
                default -> null;
            };
        } catch (OperationException exception) {
            panic(operator.position(), exception.message(), operand.type());
        }

        return null;
    }

    @Override
    public RuntimeObject visit(FunctionCallExpression expression) {
        return expression.function().accept(this).call(inherit(),
                expression.position(), expression.arguments());
    }

    @Override
    public RuntimeObject visit(GroupingExpression expression) {
        return expression.expression().accept(this);
    }

    @Override
    public RuntimeObject visit(IdentifierExpression expression) {
        return environment.get(expression.name(), expression.position());
    }

    @Override
    public RuntimeObject visit(LiteralExpression expression) {
        var o = expression.value();

        if (o instanceof Double value) {
            return new NumberObject(value);
        }

        if (o instanceof String value) {
            return new StringObject(value);
        }

        if (o instanceof Boolean value) {
            return new BoolObject(value);
        }

        return null;
    }

    @Override
    public void visit(BlockStatement statement) {
        inherit().execute(statement.statements().copy());
    }

    private Executor inherit() {
        return new Executor(handler, output, input, environment.inherit());
    }

    @Override
    public void visit(DefinitionStatement statement) {
        var name = statement.name();
        environment.declare(name, new FunctionObject(name, statement.parameters(), statement.body()),
                statement.position());
    }

    @Override
    public void visit(IfElseStatement statement) {
        if (statement.condition().accept(this).asBool().value()) {
            statement.ifBody().accept(this);
        } else {
            var body = statement.elseBody();
            if (body != null) {
                body.accept(this);
            }
        }
    }

    @Override
    public void visit(WhileStatement statement) {
        while (statement.condition().accept(this).asBool().value()) {
            statement.body().accept(this);
        }
    }

    @Override
    public void visit(ForStatement statement) {
        statement.initializer().accept(this);
        while (statement.condition().accept(this).asBool().value()) {
            statement.body().accept(this);
            statement.increment().accept(this);
        }
    }

    @Override
    public void visit(ReturnStatement statement) {
        new AssignmentStatement(FunctionObject.RETURN, statement.value(), statement.position()).accept(this);
    }

    @Override
    public void visit(DeclarationStatement statement) {
        environment.declare(statement.identifier(), statement.value().accept(this), statement.position());
    }

    @Override
    public void visit(AssignmentStatement statement) {
        environment.assign(statement.identifier(), statement.value().accept(this), statement.position());
    }

    @Override
    public void visit(ExpressionStatement statement) {
        statement.value().accept(this);
    }

    @Override
    public RuntimeObject visit(EmptyExpression expression) {
        panic(expression.position(), Message.NIL);
        return null;
    }

    @Override
    public RuntimeObject visit(NilExpression expression) {
        return null;
    }

    private void panic(TokenPosition position, String message, Object... args) {
        handler.panic(ErrorHandler.Stage.EXECUTE, ErrorHandler.Type.INVALID_TYPE, position, message, args);
    }
}
