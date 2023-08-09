package com.deltalang.statement.matcher.logic;

import com.deltalang.error.ErrorHandler;
import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.logic.IfElseStatement;
import com.deltalang.statement.matcher.StatementMatcher;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

public class IfElseMatcher extends StatementMatcher {
    @Override
    public Statement match(Sequence<Token> tokens, ErrorHandler handler) {
        if (!require(tokens, TokenType.IF)) return null;

        var condition = expression(tokens, handler);
        if (condition == null) return null;

        var ifBody = statement(tokens, handler);
        if (ifBody == null) return null;

        if (!require(tokens, TokenType.ELSE)) {
            return new IfElseStatement(condition, ifBody, null);
        }

        var elseBody = statement(tokens, handler);
        if (elseBody == null) return null;

        return new IfElseStatement(condition, ifBody, elseBody);
    }
}
