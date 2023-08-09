package com.deltalang.statement;

public interface Statement {
    void accept(StatementVisitor visitor);
}
