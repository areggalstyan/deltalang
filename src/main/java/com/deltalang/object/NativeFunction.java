package com.deltalang.object;

import java.util.Map;

@FunctionalInterface
public interface NativeFunction {
    RuntimeObject execute(Map<String, RuntimeObject> arguments);
}
