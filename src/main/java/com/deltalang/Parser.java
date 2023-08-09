package com.deltalang;

import com.deltalang.error.ErrorHandler;
import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.StatementType;
import com.deltalang.token.Token;

import java.util.ArrayList;

public class Parser {
    Sequence<Token> tokens;
    ErrorHandler handler;

    public Parser(Sequence<Token> tokens, ErrorHandler handler) {
        this.tokens = tokens;
        this.handler = handler;
    }

    public Sequence<Statement> parse() {
        var statements = new ArrayList<Statement>();

        while (tokens.hasNext()) {
            var matchers = StatementType.matchers();
            var unexpected = true;

            while (matchers.hasNext()) {
                var statement = matchers.consume().match(tokens, handler);
                if (statement != null) {
                    unexpected = false;
                    statements.add(statement);
                    break;
                }
            }

            if (unexpected) {
                if (tokens.hasNext()) {
                    tokens.consume();
                }

                error(tokens.current());
            }
        }

        return Sequence.of(statements);
    }

    private void error(Token token) {
        handler.report(ErrorHandler.Stage.PARSE, ErrorHandler.Type.INVALID_TOKEN, token.position(), token.type());
    }
}
