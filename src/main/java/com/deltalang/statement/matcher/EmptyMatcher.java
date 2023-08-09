package com.deltalang.statement.matcher;

import com.deltalang.error.ErrorHandler;
import com.deltalang.io.Sequence;
import com.deltalang.statement.EmptyStatement;
import com.deltalang.statement.Statement;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

public class EmptyMatcher extends StatementMatcher {
    @Override
    public Statement match(Sequence<Token> tokens, ErrorHandler handler) {
        if (!require(tokens, TokenType.SEMICOLON)) return null;
        return new EmptyStatement();
    }
}
