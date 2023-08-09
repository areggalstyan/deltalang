package com.deltalang.statement.logic;

import com.deltalang.expression.Expression;
import com.deltalang.statement.Statement;
import com.deltalang.statement.StatementVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WhileStatement implements Statement {
    Expression condition;
    Statement body;

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
}
