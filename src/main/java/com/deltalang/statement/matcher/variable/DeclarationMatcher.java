package com.deltalang.statement.matcher.variable;

import com.deltalang.error.ErrorHandler;
import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.matcher.StatementMatcher;
import com.deltalang.statement.variable.DeclarationStatement;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

public class DeclarationMatcher extends StatementMatcher {
    @Override
    public Statement match(Sequence<Token> tokens, ErrorHandler handler) {
        if (!require(tokens, TokenType.VAR) || !require(tokens, TokenType.IDENTIFIER)) return null;
        var identifier = (String) tokens.current().asLiteral().value();

        if (require(tokens, TokenType.SEMICOLON)) {
            return new DeclarationStatement(identifier, tokens.current().position());
        }

        if (!require(tokens, TokenType.EQUAL)) return null;

        var expression = expression(tokens, handler);
        if (expression == null) return null;

        return new DeclarationStatement(identifier, expression, tokens.current().position());
    }
}
