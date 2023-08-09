package com.deltalang.statement.variable;

import com.deltalang.expression.Expression;
import com.deltalang.statement.Statement;
import com.deltalang.statement.StatementVisitor;
import com.deltalang.token.TokenPosition;
import lombok.Getter;

@Getter
public class AssignmentStatement implements Statement {
    String identifier;
    Expression value;
    TokenPosition position;

    public AssignmentStatement(String identifier, Expression value, TokenPosition position) {
        this.identifier = identifier;
        this.value = value.nillable();
        this.position = position;
    }

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
}
