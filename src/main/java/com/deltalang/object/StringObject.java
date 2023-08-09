package com.deltalang.object;

import com.deltalang.error.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StringObject extends RuntimeObject {
    String value;

    @Override
    public RuntimeObject sum(RuntimeObject o) {
        return newString(value + o.asString(Message.ADD).value);
    }

    @Override
    public RuntimeObject multiply(RuntimeObject o) {
        return newString(value.repeat((int) o.asNumber(Message.MULTIPLY).value()));
    }

    @Override
    public int compare(RuntimeObject o) {
        return value.compareTo(o.asString(Message.COMPARE).value);
    }

    @Override
    public StringObject asString() {
        return this;
    }

    @Override
    public BoolObject asBool() {
        return newBool(!value.isEmpty());
    }

    @Override
    protected boolean isString() {
        return true;
    }
}
