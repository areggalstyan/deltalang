package com.deltalang.expression;

import com.deltalang.token.TokenPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmptyExpression implements Expression {
    TokenPosition position;

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Expression nillable() {
        return new NilExpression();
    }
}
