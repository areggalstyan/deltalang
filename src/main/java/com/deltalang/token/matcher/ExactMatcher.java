package com.deltalang.token.matcher;

import com.deltalang.io.Sequence;
import com.deltalang.token.Token;
import com.deltalang.token.TokenPosition;
import com.deltalang.token.TokenType;
import lombok.Getter;

@Getter
public class ExactMatcher extends TokenMatcher {
    String string;
    int size;

    public ExactMatcher(TokenType type, String string) {
        super(type);
        this.string = string;
        size = string.length();
    }

    @Override
    public Token match(Sequence<Character> characters, TokenPosition position) {
        if (characters.remaining() < size) return null;

        for (int i = 0; i < size; i++) {
            if (characters.next(i + 1) != string.charAt(i)) return null;
        }

        for (int i = 0; i < size; i++) {
            characters.consume();
        }

        return new Token(type(), position);
    }

    @Override
    public int priority() {
        return size;
    }
}
