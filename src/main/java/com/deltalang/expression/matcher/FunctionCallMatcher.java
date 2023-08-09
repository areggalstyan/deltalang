package com.deltalang.expression.matcher;

import com.deltalang.error.ErrorHandler;
import com.deltalang.expression.Expression;
import com.deltalang.expression.ExpressionType;
import com.deltalang.expression.FunctionCallExpression;
import com.deltalang.io.Sequence;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

import java.util.ArrayList;

public class FunctionCallMatcher implements ExpressionMatcher {
    ExpressionMatcher next;

    public FunctionCallMatcher(ExpressionType next) {
        this.next = next.matcher();
    }

    @Override
    public Expression match(Sequence<Token> tokens, ErrorHandler handler) {
        var operand = next.match(tokens, handler);

        while (tokens.hasNext() && tokens.next().type() == TokenType.LEFT_PARENTHESIS) {
            tokens.consume();

            var position = tokens.current().position();
            var arguments = new ArrayList<Expression>();

            while (tokens.hasNext() && tokens.next().type() != TokenType.RIGHT_PARENTHESIS) {
                arguments.add(ExpressionType.BASE.matcher().match(tokens, handler));

                if (!tokens.hasNext() || tokens.next().type() != TokenType.COMMA) break;
                tokens.consume();
            }

            if (!tokens.hasNext() || tokens.next().type() != TokenType.RIGHT_PARENTHESIS) return null;
            tokens.consume();

            operand = new FunctionCallExpression(operand, Sequence.of(arguments), position);
        }

        return operand;
    }
}
