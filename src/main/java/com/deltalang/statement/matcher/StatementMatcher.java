package com.deltalang.statement.matcher;

import com.deltalang.error.ErrorHandler;
import com.deltalang.expression.Expression;
import com.deltalang.expression.ExpressionType;
import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.StatementType;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

import java.util.ArrayList;

public abstract class StatementMatcher {
    public abstract Statement match(Sequence<Token> tokens, ErrorHandler handler);

    protected Expression expression(Sequence<Token> tokens, ErrorHandler handler) {
        return expression(tokens, handler, TokenType.SEMICOLON);
    }

    protected Expression expression(Sequence<Token> tokens, ErrorHandler handler, TokenType stop) {
        var expressionTokens = new ArrayList<Token>();
        while (tokens.hasNext() && tokens.next().type() != stop) {
            expressionTokens.add(tokens.consume());
        }

        if (!tokens.hasNext()) return null;
        tokens.consume();

        return ExpressionType.BASE.matcher().match(Sequence.of(expressionTokens), handler);
    }

    protected Statement statement(Sequence<Token> tokens, ErrorHandler handler) {
        var matchers = StatementType.matchers();

        while (matchers.hasNext()) {
            var statement = matchers.consume().match(tokens, handler);
            if (statement != null) return statement;
        }

        return null;
    }

    protected boolean require(Sequence<Token> tokens, TokenType type) {
        if (!tokens.hasNext() || tokens.next().type() != type) return false;
        tokens.consume();

        return true;
    }
}
