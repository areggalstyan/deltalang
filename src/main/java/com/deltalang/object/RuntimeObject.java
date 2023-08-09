package com.deltalang.object;

import com.deltalang.Executor;
import com.deltalang.error.Message;
import com.deltalang.error.OperationException;
import com.deltalang.expression.Expression;
import com.deltalang.io.Sequence;
import com.deltalang.statement.Statement;
import com.deltalang.token.TokenPosition;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RuntimeObject {
    public RuntimeObject sum(RuntimeObject o) {
        throw new OperationException(Message.ADD);
    }

    public RuntimeObject subtract(RuntimeObject o) {
        throw new OperationException(Message.SUBTRACT);
    }

    public RuntimeObject multiply(RuntimeObject o) {
        throw new OperationException(Message.MULTIPLY);
    }

    public RuntimeObject divide(RuntimeObject o) {
        throw new OperationException(Message.DIVIDE);
    }

    public RuntimeObject modulo(RuntimeObject o) {
        throw new OperationException(Message.MODULO);
    }

    public int compare(RuntimeObject o) {
        throw new OperationException(Message.COMPARE);
    }

    public RuntimeObject inverse() {
        throw new OperationException(Message.INVERSE);
    }

    public RuntimeObject negate() {
        throw new OperationException(Message.NEGATE);
    }

    public RuntimeObject call(Executor executor, TokenPosition position, Sequence<Expression> arguments) {
        throw new OperationException(Message.CALL);
    }

    public NumberObject asNumber() {
        return null;
    }

    public StringObject asString() {
        return null;
    }

    public BoolObject asBool() {
        return null;
    }

    public FunctionObject asFunction() {
        return null;
    }

    public String type() {
        var name = getClass().getSimpleName();
        return name.substring(0, name.length() - 6).toLowerCase();
    }

    protected boolean isNumber() {
        return false;
    }

    protected boolean isString() {
        return false;
    }

    protected boolean isBool() {
        return false;
    }

    protected boolean isFunction() {
        return false;
    }

    protected NumberObject asNumber(String message) {
        var result = asNumber();
        if (result != null) return result;
        throw new OperationException(message);
    }

    protected StringObject asString(String message) {
        var result = asString();
        if (result != null) return result;
        throw new OperationException(message);
    }

    protected BoolObject asBool(String message) {
        var result = asBool();
        if (result != null) return result;
        throw new OperationException(message);
    }

    protected NumberObject newNumber(double value) {
        return new NumberObject(value);
    }

    protected StringObject newString(String value) {
        return new StringObject(value);
    }

    protected BoolObject newBool(boolean value) {
        return new BoolObject(value);
    }

    protected FunctionObject newFunction(String name, Sequence<String> parameters, Statement statement) {
        return new FunctionObject(name, parameters, statement);
    }
}
