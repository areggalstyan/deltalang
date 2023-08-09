package com.deltalang.statement.matcher.logic;

import com.deltalang.error.ErrorHandler;
import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.logic.ForStatement;
import com.deltalang.statement.matcher.StatementMatcher;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

public class ForMatcher extends StatementMatcher {
    @Override
    public Statement match(Sequence<Token> tokens, ErrorHandler handler) {
        if (!require(tokens, TokenType.FOR)) return null;

        var initializer = statement(tokens, handler);
        if (initializer == null) return null;

        var condition = expression(tokens, handler);
        if (condition == null) return null;

        var increment = statement(tokens, handler);
        if (increment == null) return null;

        var body = statement(tokens, handler);
        if (body == null) return null;

        return new ForStatement(initializer, condition, increment, body);
    }
}
