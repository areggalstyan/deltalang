package com.deltalang.expression;

import com.deltalang.expression.matcher.*;
import com.deltalang.token.TokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpressionType {
    PRIMARY(new PrimaryMatcher()),
    FUNCTION_CALL(new FunctionCallMatcher(PRIMARY)),
    UNARY(new UnaryMatcher(FUNCTION_CALL,
            TokenType.BANG, TokenType.MINUS)),
    MULTIPLY_DIVIDE_MODULO(new BinaryMatcher(UNARY,
            TokenType.MULTIPLY, TokenType.DIVIDE, TokenType.MODULO)),
    ADD_SUBTRACT(new BinaryMatcher(MULTIPLY_DIVIDE_MODULO,
            TokenType.PLUS, TokenType.MINUS)),
    COMPARISON(new BinaryMatcher(ADD_SUBTRACT,
            TokenType.GREATER, TokenType.GREATER_EQUAL,
            TokenType.LESS, TokenType.LESS_EQUAL)),
    EQUALITY(new BinaryMatcher(COMPARISON,
            TokenType.EQUAL_EQUAL, TokenType.BANG_EQUAL));

    public static final ExpressionType BASE = EQUALITY;

    ExpressionMatcher matcher;
}
