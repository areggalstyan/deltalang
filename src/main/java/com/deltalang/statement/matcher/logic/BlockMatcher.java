package com.deltalang.statement.matcher.logic;

import com.deltalang.error.ErrorHandler;
import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.logic.BlockStatement;
import com.deltalang.statement.matcher.StatementMatcher;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

import java.util.ArrayList;

public class BlockMatcher extends StatementMatcher {
    @Override
    public Statement match(Sequence<Token> tokens, ErrorHandler handler) {
        if (!require(tokens, TokenType.LEFT_CURLY)) return null;

        var statements = new ArrayList<Statement>();
        while (!require(tokens, TokenType.RIGHT_CURLY)) {
            var statement = statement(tokens, handler);
            if (statement == null) return null;
            statements.add(statement);
        }

        return new BlockStatement(Sequence.of(statements));
    }
}
