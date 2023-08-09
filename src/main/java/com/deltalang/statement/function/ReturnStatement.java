package com.deltalang.statement.function;

import com.deltalang.expression.Expression;
import com.deltalang.statement.Statement;
import com.deltalang.statement.StatementVisitor;
import com.deltalang.token.TokenPosition;
import lombok.Getter;

@Getter
public class ReturnStatement implements Statement {
    Expression value;
    TokenPosition position;

    public ReturnStatement(Expression value, TokenPosition position) {
        this.value = value.nillable();
        this.position = position;
    }

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
}
