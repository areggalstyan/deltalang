package com.deltalang.token.matcher;

import com.deltalang.io.Sequence;
import com.deltalang.token.LiteralToken;
import com.deltalang.token.Token;
import com.deltalang.token.TokenPosition;
import com.deltalang.token.TokenType;

public class IdentifierMatcher extends TokenMatcher {
    public IdentifierMatcher(TokenType type) {
        super(type);
    }

    @Override
    public Token match(Sequence<Character> characters, TokenPosition position) {
        if (!characters.hasNext() || !isValidFirst(characters.next())) return null;

        var literal = new StringBuilder();
        while (characters.hasNext() && isValid(characters.next())) {
            literal.append(characters.consume());
        }

        return new LiteralToken(type(), position, literal.toString());
    }

    private boolean isValidFirst(char character) {
        return Character.isAlphabetic(character) || character == '_';
    }

    private boolean isValid(char character) {
        return isValidFirst(character) || Character.isDigit(character);
    }
}
