package com.deltalang.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Message {
    public final String NEGATE = "`bool` or `number` expected in logical negation, `%s` found";
    public final String INVERSE = "`number` expected in arithmetical negation, `%s` found";
    public final String ADD = "[`number`, `number`] or [`string`, `string`] " +
            "expected in summation, [`%s`, `%s`] found";
    public final String SUBTRACT = "[`number`, `number`] expected in subtraction, [`%s`, `%s`] found";
    public final String MULTIPLY = "[`number`, `number`] or [`number`, `string`] " +
            "expected in multiplication, [`%s`, `%s`] found";
    public final String DIVIDE = "[`number`, `number`] expected in division, [`%s`, `%s`] found";
    public final String MODULO = "[`number`, `number`] expected in modulo, [`%s`, `%s`] found";
    public final String COMPARE = "[`number`, `number`], [`string`, `string`] or [`bool`, `bool`] " +
            "expected in comparison or equality check, `%s` found";
    public final String REDECLARATION = "`%s` is already declared in this scope";
    public final String UNDECLARED_ACCESS = "`%s` is not declared in this scope";
    public final String OPERAND_MISSING = "Missing operand for operation `%s`";
    public final String CALL = "`function` expected in call, `%s` found";
    public final String NIL = "`any` expected, `nil` found";
}
