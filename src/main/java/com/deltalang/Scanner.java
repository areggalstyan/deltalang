package com.deltalang;

import com.deltalang.error.ErrorHandler;
import com.deltalang.io.Sequence;
import com.deltalang.token.Token;
import com.deltalang.token.TokenPosition;
import com.deltalang.token.TokenType;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class Scanner {
    Sequence<Character> characters;
    ErrorHandler handler;

    public Sequence<Token> scan() {
        var tokens = new ArrayList<Token>();
        var line = 1;
        var column = 1;

        while (characters.hasNext()) {
            if (Character.isWhitespace(characters.next())) {
                var space = characters.consume();

                if (space == '\n') {
                    line++;
                    column = 1;
                } else {
                    column++;
                }

                continue;
            }

            var unexpected = true;
            var matchers = TokenType.matchers();

            while (matchers.hasNext()) {
                var previousRemaining = characters.remaining();
                var token = matchers.consume().match(characters, new TokenPosition(line, column));
                column += previousRemaining - characters.remaining();

                if (token != null) {
                    unexpected = false;

                    if (token.type() == TokenType.COMMENT) {
                        while (characters.hasNext() && characters.next() != '\n') {
                            characters.consume();
                        }
                    } else {
                        tokens.add(token);
                    }

                    break;
                }
            }

            if (unexpected) {
                error(line, column++);
                if (characters.hasNext()) {
                    characters.consume();
                }
            }
        }

        return Sequence.of(tokens);
    }

    private void error(int line, int column) {
        handler.report(ErrorHandler.Stage.SCAN, ErrorHandler.Type.INVALID_CHARACTER, line, column, characters.next());
    }
}
