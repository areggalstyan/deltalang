package com.deltalang.token;

import com.deltalang.io.Sequence;
import com.deltalang.token.matcher.*;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Getter
public enum TokenType {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    COMMENT("//"),
    VAR("var"),
    EQUAL("="),
    MODULO("%"),
    EQUAL_EQUAL("=="),
    BANG_EQUAL("!="),
    GREATER(">"),
    LESS("<"),
    GREATER_EQUAL(">="),
    LESS_EQUAL("<="),
    SEMICOLON(";"),
    LEFT_PARENTHESIS("("),
    RIGHT_PARENTHESIS(")"),
    LEFT_CURLY("{"),
    RIGHT_CURLY("}"),
    BANG("!"),
    IF("if"),
    ELSE("else"),
    WHILE("while"),
    FOR("for"),
    TRUE("true"),
    FALSE("false"),
    NIL("nil"),
    DEF("def"),
    RETURN("return"),
    COMMA(","),
    STRING(StringMatcher::new),
    NUMBER(NumberMatcher::new),
    IDENTIFIER(IdentifierMatcher::new);

    private static final List<TokenMatcher> matchers =
            Arrays.stream(values()).map(TokenType::matcher).sorted().toList();

    TokenMatcher matcher;

    public static Sequence<TokenMatcher> matchers() {
        return Sequence.of(matchers);
    }

    TokenType(String string) {
        matcher = new ExactMatcher(this, string);
    }

    TokenType(Function<TokenType, ? extends TokenMatcher> supplier) {
        matcher = supplier.apply(this);
    }
}
