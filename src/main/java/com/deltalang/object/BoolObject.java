package com.deltalang.object;

import com.deltalang.error.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoolObject extends RuntimeObject {
    boolean value;

    @Override
    public RuntimeObject sum(RuntimeObject o) {
        if (o.isString()) return asString().sum(o);
        return asNumber().sum(o);
    }

    @Override
    public RuntimeObject subtract(RuntimeObject o) {
        return asNumber().subtract(o);
    }

    @Override
    public RuntimeObject multiply(RuntimeObject o) {
        return asNumber().multiply(o);
    }

    @Override
    public RuntimeObject divide(RuntimeObject o) {
        return asNumber().multiply(o);
    }

    @Override
    public int compare(RuntimeObject o) {
        return Boolean.compare(value, o.asBool(Message.COMPARE).value);
    }

    @Override
    public RuntimeObject inverse() {
        return asNumber().inverse();
    }

    @Override
    public RuntimeObject negate() {
        return newBool(!value);
    }

    @Override
    public NumberObject asNumber() {
        return newNumber(value ? 1 : 0);
    }

    @Override
    public StringObject asString() {
        return newString(Boolean.toString(value));
    }

    @Override
    public BoolObject asBool() {
        return this;
    }

    @Override
    protected boolean isBool() {
        return true;
    }
}
