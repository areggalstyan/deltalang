package com.deltalang.expression.matcher;

import com.deltalang.error.ErrorHandler;
import com.deltalang.expression.Expression;
import com.deltalang.io.Sequence;
import com.deltalang.token.Token;

public interface ExpressionMatcher {
    Expression match(Sequence<Token> tokens, ErrorHandler handler);
}
