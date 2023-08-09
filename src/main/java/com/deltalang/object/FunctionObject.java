package com.deltalang.object;

import com.deltalang.Executor;
import com.deltalang.expression.Expression;
import com.deltalang.expression.IdentifierExpression;
import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.variable.DeclarationStatement;
import com.deltalang.token.TokenPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FunctionObject extends RuntimeObject {
    public static final String RETURN = "$return";

    String name;
    Sequence<String> parameters;
    Statement body;

    @Override
    public RuntimeObject call(Executor executor, TokenPosition position, Sequence<Expression> arguments) {
        var argumentsCopy = arguments.copy();
        var parametersCopy = parameters.copy();

        while (argumentsCopy.hasNext() && parametersCopy.hasNext()) {
            new DeclarationStatement(parametersCopy.consume(), argumentsCopy.consume(), position).accept(executor);
        }

        new DeclarationStatement(RETURN, position).accept(executor);
        body.accept(executor);
        return new IdentifierExpression(RETURN, position).accept(executor);
    }

    @Override
    public RuntimeObject sum(RuntimeObject o) {
        return asString().sum(o);
    }

    @Override
    public StringObject asString() {
        return newString(name);
    }

    @Override
    public FunctionObject asFunction() {
        return this;
    }

    @Override
    protected boolean isFunction() {
        return true;
    }
}
