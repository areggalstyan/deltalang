package com.deltalang.statement.matcher.function;

import com.deltalang.error.ErrorHandler;
import com.deltalang.expression.NilExpression;
import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.function.ReturnStatement;
import com.deltalang.statement.matcher.StatementMatcher;
import com.deltalang.token.Token;
import com.deltalang.token.TokenType;

public class ReturnMatcher extends StatementMatcher {
    @Override
    public Statement match(Sequence<Token> tokens, ErrorHandler handler) {
        if (!require(tokens, TokenType.RETURN)) return null;
        if (require(tokens, TokenType.SEMICOLON)) {
            return new ReturnStatement(new NilExpression(), tokens.current().position());
        }

        var expression = expression(tokens, handler);
        if (expression == null) return null;

        return new ReturnStatement(expression, tokens.current().position());
    }
}
