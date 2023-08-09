package com.deltalang.statement.matcher.function;

import com.deltalang.error.ErrorHandler;
import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.function.DefinitionStatement;
import com.deltalang.statement.matcher.StatementMatcher;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

import java.util.ArrayList;

public class DefinitionMatcher extends StatementMatcher {
    @Override
    public Statement match(Sequence<Token> tokens, ErrorHandler handler) {
        if (!require(tokens, TokenType.DEF) || !require(tokens, TokenType.IDENTIFIER)) return null;
        var name = (String) tokens.current().asLiteral().value();

        if (!require(tokens, TokenType.LEFT_PARENTHESIS)) return null;

        var parameters = new ArrayList<String>();
        while (require(tokens, TokenType.IDENTIFIER)) {
            parameters.add((String) tokens.current().asLiteral().value());
            if (!require(tokens, TokenType.COMMA)) break;
        }

        if (!require(tokens, TokenType.RIGHT_PARENTHESIS)) return null;

        var body = statement(tokens, handler);
        if (body == null) return null;

        return new DefinitionStatement(name, Sequence.of(parameters), body, tokens.current().position());
    }
}
