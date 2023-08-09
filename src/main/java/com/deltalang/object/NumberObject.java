package com.deltalang.object;

import com.deltalang.error.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.DecimalFormat;

@Getter
@AllArgsConstructor
public class NumberObject extends RuntimeObject {
    private static final DecimalFormat format = new DecimalFormat("0.#");

    double value;

    @Override
    public RuntimeObject sum(RuntimeObject o) {
        if (o.isString()) return asString().sum(o);
        return newNumber(value + o.asNumber(Message.ADD).value);
    }

    @Override
    public RuntimeObject subtract(RuntimeObject o) {
        return newNumber(value - o.asNumber(Message.SUBTRACT).value);
    }

    @Override
    public RuntimeObject multiply(RuntimeObject o) {
        if (o.isString()) return o.multiply(this);
        return newNumber(value * o.asNumber(Message.MULTIPLY).value);
    }

    @Override
    public RuntimeObject divide(RuntimeObject o) {
        return newNumber(value / o.asNumber(Message.DIVIDE).value);
    }

    @Override
    public RuntimeObject modulo(RuntimeObject o) {
        return newNumber(value % o.asNumber(Message.MODULO).value);
    }

    @Override
    public int compare(RuntimeObject o) {
        return Double.compare(value, o.asNumber(Message.COMPARE).value);
    }

    @Override
    public RuntimeObject inverse() {
        return newNumber(-value);
    }

    @Override
    public RuntimeObject negate() {
        return asBool().negate();
    }

    @Override
    public NumberObject asNumber() {
        return this;
    }

    @Override
    public StringObject asString() {
        return newString(format.format(value));
    }

    @Override
    public BoolObject asBool() {
        return newBool(value != 0);
    }

    @Override
    protected boolean isNumber() {
        return true;
    }
}
