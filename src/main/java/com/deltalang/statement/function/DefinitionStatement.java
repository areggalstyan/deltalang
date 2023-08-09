package com.deltalang.statement.function;

import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.statement.StatementVisitor;
import com.deltalang.token.TokenPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DefinitionStatement implements Statement {
    String name;
    Sequence<String> parameters;
    Statement body;
    TokenPosition position;

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
}
