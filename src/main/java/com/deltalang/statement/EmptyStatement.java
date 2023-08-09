package com.deltalang.statement;

public class EmptyStatement implements Statement {
    @Override
    public void accept(StatementVisitor visitor) {
    }
}
