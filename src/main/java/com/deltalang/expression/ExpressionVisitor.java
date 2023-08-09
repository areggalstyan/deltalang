package com.deltalang.expression;

public interface ExpressionVisitor<R> {
    R visit(BinaryExpression expression);
    R visit(UnaryExpression expression);
    R visit(FunctionCallExpression expression);
    R visit(GroupingExpression expression);
    R visit(LiteralExpression expression);
    R visit(IdentifierExpression expression);
    R visit(EmptyExpression expression);
    R visit(NilExpression expression);
}
