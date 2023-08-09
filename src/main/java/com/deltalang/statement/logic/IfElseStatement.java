package com.deltalang.statement.logic;

import com.deltalang.expression.Expression;
import com.deltalang.statement.Statement;
import com.deltalang.statement.StatementVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IfElseStatement implements Statement {
    Expression condition;
    Statement ifBody;
    Statement elseBody;

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
}
