package com.deltalang.statement;

import com.deltalang.io.Sequence;
import com.deltalang.statement.matcher.EmptyMatcher;
import com.deltalang.statement.matcher.ExpressionMatcher;
import com.deltalang.statement.matcher.StatementMatcher;
import com.deltalang.statement.matcher.function.DefinitionMatcher;
import com.deltalang.statement.matcher.function.ReturnMatcher;
import com.deltalang.statement.matcher.logic.BlockMatcher;
import com.deltalang.statement.matcher.logic.ForMatcher;
import com.deltalang.statement.matcher.logic.IfElseMatcher;
import com.deltalang.statement.matcher.logic.WhileMatcher;
import com.deltalang.statement.matcher.variable.AssignmentMatcher;
import com.deltalang.statement.matcher.variable.DeclarationMatcher;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum StatementType {
    BLOCK(new BlockMatcher()),
    IF_ELSE(new IfElseMatcher()),
    WHILE(new WhileMatcher()),
    FOR(new ForMatcher()),
    RETURN(new ReturnMatcher()),
    DEFINITION(new DefinitionMatcher()),
    DECLARATION(new DeclarationMatcher()),
    ASSIGNMENT(new AssignmentMatcher()),
    EXPRESSION(new ExpressionMatcher()),
    EMPTY(new EmptyMatcher());

    StatementMatcher matcher;

    private static final List<StatementMatcher> matchers =
            Arrays.stream(values()).map(StatementType::matcher).toList();

    public static Sequence<StatementMatcher> matchers() {
        return Sequence.of(matchers);
    }
}
