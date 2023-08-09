package com.deltalang.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Token {
    TokenType type;
    TokenPosition position;

    public LiteralToken asLiteral() {
        return null;
    }
}
