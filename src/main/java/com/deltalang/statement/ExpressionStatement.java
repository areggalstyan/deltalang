package com.deltalang.statement;

import com.deltalang.expression.Expression;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExpressionStatement implements Statement {
    Expression value;

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
}
