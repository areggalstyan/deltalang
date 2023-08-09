package com.deltalang.statement.matcher;

import com.deltalang.error.ErrorHandler;
import com.deltalang.io.Sequence;
import com.deltalang.statement.ExpressionStatement;
import com.deltalang.statement.Statement;
import com.deltalang.token.Token;

public class ExpressionMatcher extends StatementMatcher {
    @Override
    public Statement match(Sequence<Token> tokens, ErrorHandler handler) {
        var value = expression(tokens, handler);
        if (value == null) return null;

        return new ExpressionStatement(value);
    }
}
