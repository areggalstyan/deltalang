package com.deltalang.token.matcher;

import com.deltalang.io.Sequence;
import com.deltalang.token.Token;
import com.deltalang.token.TokenPosition;
import com.deltalang.token.TokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class TokenMatcher implements Comparable<TokenMatcher> {
    TokenType type;

    public abstract Token match(Sequence<Character> characters, TokenPosition position);

    public int priority() {
        return 0;
    }

    @Override
    public int compareTo(TokenMatcher o) {
        return o.priority() - priority();
    }
}
