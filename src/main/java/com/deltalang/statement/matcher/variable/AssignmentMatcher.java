package com.deltalang.statement.matcher.variable;

import com.deltalang.error.ErrorHandler;
import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.matcher.StatementMatcher;
import com.deltalang.statement.variable.AssignmentStatement;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

public class AssignmentMatcher extends StatementMatcher {
    @Override
    public Statement match(Sequence<Token> tokens, ErrorHandler handler) {
        if (!require(tokens, TokenType.IDENTIFIER)) return null;
        if (!require(tokens, TokenType.EQUAL)) {
            tokens.unconsume();
            return null;
        }

        var identifier = (String) tokens.previous().asLiteral().value();
        var expression = expression(tokens, handler);

        if (expression == null) return null;
        return new AssignmentStatement(identifier, expression, tokens.current().position());
    }
}
