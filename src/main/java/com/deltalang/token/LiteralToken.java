package com.deltalang.token;

import lombok.Getter;

@Getter
public class LiteralToken extends Token {
    Object value;

    public LiteralToken(TokenType type, TokenPosition position, Object value) {
        super(type, position);
        this.value = value;
    }

    @Override
    public LiteralToken asLiteral() {
        return this;
    }
}
