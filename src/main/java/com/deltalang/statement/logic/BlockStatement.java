package com.deltalang.statement.logic;

import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.StatementVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlockStatement implements Statement {
    Sequence<Statement> statements;

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
}
