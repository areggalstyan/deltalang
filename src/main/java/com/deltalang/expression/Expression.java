package com.deltalang.expression;

public interface Expression {
    <R> R accept(ExpressionVisitor<R> visitor);

    default BinaryExpression asBinary() {
        return null;
    }

    default UnaryExpression asUnary() {
        return null;
    }

    default GroupingExpression asGrouping() {
        return null;
    }

    default IdentifierExpression asIdentifier() {
        return null;
    }

    default LiteralExpression asLiteral() {
        return null;
    }

    default FunctionCallExpression asFunctionCall() {
        return null;
    }

    default boolean isBinary() {
        return false;
    }

    default boolean isUnary() {
        return false;
    }

    default boolean isGrouping() {
        return false;
    }

    default boolean isIdentifier() {
        return false;
    }

    default boolean isLiteral() {
        return false;
    }

    default boolean isFunctionCall() {
        return false;
    }

    default Expression nillable() {
        return this;
    }
}
