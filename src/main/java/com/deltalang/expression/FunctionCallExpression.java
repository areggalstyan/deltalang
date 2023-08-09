package com.deltalang.expression;

import com.deltalang.io.Sequence;
import com.deltalang.token.TokenPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FunctionCallExpression implements Expression {
    Expression function;
    Sequence<Expression> arguments;
    TokenPosition position;

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public FunctionCallExpression asFunctionCall() {
        return this;
    }

    @Override
    public boolean isFunctionCall() {
        return true;
    }
}
