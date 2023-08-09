package com.deltalang.expression;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupingExpression implements Expression {
    Expression expression;

    @Override
    public <R> R accept(ExpressionVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public GroupingExpression asGrouping() {
        return this;
    }

    @Override
    public boolean isGrouping() {
        return true;
    }
}
