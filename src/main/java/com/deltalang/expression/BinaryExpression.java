package com.deltalang.expression;

import com.deltalang.token.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BinaryExpression implements Expression {
    Expression left;
    Token operator;
    Expression right;

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public BinaryExpression asBinary() {
        return this;
    }

    @Override
    public boolean isBinary() {
        return true;
    }
}
