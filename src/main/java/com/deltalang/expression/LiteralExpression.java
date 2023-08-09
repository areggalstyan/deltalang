package com.deltalang.expression;

import com.deltalang.token.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LiteralExpression implements Expression {
    Object value;

    public LiteralExpression(Token token) {
        this(token.asLiteral().value());
    }

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public LiteralExpression asLiteral() {
        return this;
    }

    @Override
    public boolean isLiteral() {
        return true;
    }
}
