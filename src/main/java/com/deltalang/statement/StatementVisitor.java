package com.deltalang.statement;

import com.deltalang.statement.function.DefinitionStatement;
import com.deltalang.statement.function.ReturnStatement;
import com.deltalang.statement.logic.BlockStatement;
import com.deltalang.statement.logic.ForStatement;
import com.deltalang.statement.logic.IfElseStatement;
import com.deltalang.statement.logic.WhileStatement;
import com.deltalang.statement.variable.AssignmentStatement;
import com.deltalang.statement.variable.DeclarationStatement;

public interface StatementVisitor {
    void visit(BlockStatement statement);
    void visit(IfElseStatement statement);
    void visit(WhileStatement statement);
    void visit(ForStatement statement);
    void visit(ReturnStatement statement);
    void visit(DeclarationStatement statement);
    void visit(AssignmentStatement statement);
    void visit(DefinitionStatement statement);
    void visit(ExpressionStatement statement);
}
