package com.deltalang.expression.matcher;

import com.deltalang.error.ErrorHandler;
import com.deltalang.expression.*;
import com.deltalang.io.Sequence;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

public class PrimaryMatcher implements ExpressionMatcher {
    @Override
    public Expression match(Sequence<Token> tokens, ErrorHandler handler) {
        if (!tokens.hasNext()) {
            if (tokens.hasCurrent()) {
                error(tokens.current(), handler);
            }

            return null;
        }

        var next = tokens.consume();
        var type = next.type();

        return switch (type) {
            case IDENTIFIER -> new IdentifierExpression((String) next.asLiteral().value(), next.position());
            case FALSE -> new LiteralExpression(false);
            case TRUE -> new LiteralExpression(true);
            case NIL -> new EmptyExpression(next.position());
            case NUMBER, STRING -> new LiteralExpression(next);
            case LEFT_PARENTHESIS -> {
                var expression = ExpressionType.BASE.matcher().match(tokens, handler);
                if (!tokens.hasNext() || tokens.consume().type() != TokenType.RIGHT_PARENTHESIS) {
                    error(tokens.current(), handler);
                }

                yield new GroupingExpression(expression);
            }
            default -> {
                error(tokens.current(), handler);
                tokens.unconsume();
                yield null;
            }
        };
    }

    private void error(Token token, ErrorHandler handler) {
        handler.report(ErrorHandler.Stage.PARSE, ErrorHandler.Type.INVALID_TOKEN, token.position(), token.type());
    }
}
