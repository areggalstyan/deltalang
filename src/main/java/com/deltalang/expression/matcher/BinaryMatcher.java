package com.deltalang.expression.matcher;

import com.deltalang.error.ErrorHandler;
import com.deltalang.expression.BinaryExpression;
import com.deltalang.expression.Expression;
import com.deltalang.expression.ExpressionType;
import com.deltalang.io.Sequence;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

import java.util.EnumSet;
import java.util.Set;

public class BinaryMatcher implements ExpressionMatcher {
    ExpressionMatcher next;
    Set<TokenType> operators;

    public BinaryMatcher(ExpressionType next, TokenType operator, TokenType... operators) {
        this.next = next.matcher();
        this.operators = EnumSet.of(operator, operators);
    }

    @Override
    public Expression match(Sequence<Token> tokens, ErrorHandler handler) {
        var expression = next.match(tokens, handler);

        while (tokens.hasNext() && operators.contains(tokens.next().type())) {
            var operator = tokens.consume();
            var right = next.match(tokens, handler);
            expression = new BinaryExpression(expression, operator, right);
        }

        return expression;
    }
}
