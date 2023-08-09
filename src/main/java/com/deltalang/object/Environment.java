package com.deltalang.object;

import com.deltalang.error.ErrorHandler;
import com.deltalang.error.Message;
import com.deltalang.token.TokenPosition;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    ErrorHandler handler;
    Environment global;
    Map<String, RuntimeObject> local;

    public Environment(ErrorHandler handler) {
        this.handler = handler;
        global = null;
        local = new HashMap<>();
    }

    public Environment(Environment global) {
        this.global = global;
        handler = global.handler;
        local = new HashMap<>();
    }

    public Environment inherit() {
        return new Environment(this);
    }

    public void declare(String name, RuntimeObject value, TokenPosition position) {
        if (!isAbsent(name)) {
            panic(position, ErrorHandler.Type.REDECLARATION, Message.REDECLARATION, name);
        }

        local.put(name, value);
    }

    public void declareNativeFunction(String name, NativeFunction body, String... parameters) {
        local.put(name, new NativeFunctionObject(name, body, handler, parameters));
    }

    public void assign(String name, RuntimeObject value, TokenPosition position) {
        if (isAbsent(name)) {
            if (global == null) {
                panic(position, ErrorHandler.Type.UNDECLARED_ACCESS, Message.UNDECLARED_ACCESS, name);
            }

            global.assign(name, value, position);
        }

        local.put(name, value);
    }

    public RuntimeObject get(String name, TokenPosition position) {
        if (isAbsent(name)) {
            if (global == null) {
                panic(position, ErrorHandler.Type.UNDECLARED_ACCESS, Message.UNDECLARED_ACCESS, name);
            }

            return global.get(name, position);
        }

        return local.get(name);
    }

    public boolean isAbsent(String name) {
        return !local.containsKey(name);
    }

    private void panic(TokenPosition position, ErrorHandler.Type type, String message, Object... args) {
        handler.panic(ErrorHandler.Stage.EXECUTE, type, position, message, args);
    }
}
