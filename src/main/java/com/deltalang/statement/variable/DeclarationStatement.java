package com.deltalang.statement.variable;

import com.deltalang.expression.Expression;
import com.deltalang.expression.NilExpression;
import com.deltalang.statement.Statement;
import com.deltalang.statement.StatementVisitor;
import com.deltalang.token.TokenPosition;
import lombok.Getter;

@Getter
public class DeclarationStatement implements Statement {
    String identifier;
    Expression value;
    TokenPosition position;

    public DeclarationStatement(String identifier, TokenPosition position) {
        this.identifier = identifier;
        this.value = new NilExpression();
        this.position = position;
    }

    public DeclarationStatement(String identifier, Expression value, TokenPosition position) {
        this.identifier = identifier;
        this.value = value.nillable();
        this.position = position;
    }

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
}
