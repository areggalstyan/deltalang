package com.deltalang.object;

import com.deltalang.Executor;
import com.deltalang.error.ErrorHandler;
import com.deltalang.error.Message;
import com.deltalang.expression.Expression;
import com.deltalang.io.Sequence;
import com.deltalang.token.TokenPosition;

import java.util.HashMap;

public class NativeFunctionObject extends FunctionObject {
    NativeFunction body;
    ErrorHandler handler;

    public NativeFunctionObject(String name, NativeFunction body, ErrorHandler handler, String... parameters) {
        super(name, Sequence.of(parameters), null);
        this.body = body;
        this.handler = handler;
    }

    @Override
    public RuntimeObject call(Executor executor, TokenPosition position, Sequence<Expression> arguments) {
        var argumentsCopy = arguments.copy();
        var parametersCopy = parameters().copy();

        var objectArguments = new HashMap<String, RuntimeObject>();

        while (argumentsCopy.hasNext() && parametersCopy.hasNext()) {
            objectArguments.put(parametersCopy.consume(), argumentsCopy.consume().accept(executor));
        }

        try {
            return body.execute(objectArguments);
        } catch (NullPointerException exception) {
            handler.panic(ErrorHandler.Stage.EXECUTE, ErrorHandler.Type.INVALID_TYPE, position, Message.NIL);
        }

        return null;
    }
}
