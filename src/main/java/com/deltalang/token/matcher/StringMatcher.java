package com.deltalang.token.matcher;

import com.deltalang.io.Sequence;
import com.deltalang.token.LiteralToken;
import com.deltalang.token.Token;
import com.deltalang.token.TokenPosition;
import com.deltalang.token.TokenType;

public class StringMatcher extends TokenMatcher {
    public StringMatcher(TokenType type) {
        super(type);
    }

    @Override
    public Token match(Sequence<Character> characters, TokenPosition position) {
        if (!characters.hasNext() || characters.next() != '"') return null;
        characters.consume();

        var literal = new StringBuilder();
        var character = characters.consume();

        while (characters.hasNext() && character != '"') {
            literal.append(character);
            character = characters.consume();
        }

        return character == '"' ? new LiteralToken(type(), position, literal.toString()) : null;
    }
}
