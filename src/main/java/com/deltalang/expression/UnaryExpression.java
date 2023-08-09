package com.deltalang.expression;

import com.deltalang.token.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnaryExpression implements Expression {
    Expression operand;
    Token operator;

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public UnaryExpression asUnary() {
        return this;
    }

    @Override
    public boolean isUnary() {
        return true;
    }
}
