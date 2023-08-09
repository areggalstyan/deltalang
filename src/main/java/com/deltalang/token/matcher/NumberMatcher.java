package com.deltalang.token.matcher;

import com.deltalang.io.Sequence;
import com.deltalang.token.LiteralToken;
import com.deltalang.token.Token;
import com.deltalang.token.TokenPosition;
import com.deltalang.token.TokenType;

public class NumberMatcher extends TokenMatcher {
    public NumberMatcher(TokenType type) {
        super(type);
    }

    @Override
    public Token match(Sequence<Character> characters, TokenPosition position) {
        if (!characters.hasNext() || !Character.isDigit(characters.next())) return null;

        var literal = new StringBuilder();
        parseDigits(characters, literal);

        if (characters.hasNext() && characters.next() == '.') {
            literal.append(characters.consume());
            parseDigits(characters, literal);
        }

        return new LiteralToken(type(), position, Double.parseDouble(literal.toString()));
    }

    private void parseDigits(Sequence<Character> sequence, StringBuilder literal) {
        while (sequence.hasNext() && Character.isDigit(sequence.next())) {
            literal.append(sequence.consume());
        }
    }
}
