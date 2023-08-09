package com.deltalang.expression;

import com.deltalang.token.TokenPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IdentifierExpression implements Expression {
    String name;
    TokenPosition position;

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public IdentifierExpression asIdentifier() {
        return this;
    }

    @Override
    public boolean isIdentifier() {
        return true;
    }
}
