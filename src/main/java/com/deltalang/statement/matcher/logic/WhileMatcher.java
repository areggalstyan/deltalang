package com.deltalang.statement.matcher.logic;

import com.deltalang.error.ErrorHandler;
import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.logic.WhileStatement;
import com.deltalang.statement.matcher.StatementMatcher;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

public class WhileMatcher extends StatementMatcher {
    @Override
    public Statement match(Sequence<Token> tokens, ErrorHandler handler) {
        if (!require(tokens, TokenType.WHILE)) return null;

        var condition = expression(tokens, handler);
        if (condition == null) return null;

        var body = statement(tokens, handler);
        if (body == null) return null;

        return new WhileStatement(condition, body);
    }
}
