package com.deltalang.error;

import com.deltalang.io.OutputStream;
import com.deltalang.token.TokenPosition;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorHandler {
    private static final String DEFAULT_FORMAT = "[%s Error] %s at [%d:%d]: %s\n";

    OutputStream output;
    String format;

    public ErrorHandler(OutputStream output) {
        this(output, DEFAULT_FORMAT);
    }

    public void report(Stage stage, Type type, TokenPosition position, Object message, Object... args) {
        report(stage, type, position.line(), position.column(), message, args);
    }

    public void report(Stage stage, Type type, int line, int column, Object message, Object... args) {
        output.writef(format, stage, type, line, column, String.format(message.toString(), args));
    }

    public void panic(Stage stage, Type type, TokenPosition position, Object message, Object... args) {
        panic(stage, type, position.line(), position.column(), message, args);
    }

    public void panic(Stage stage, Type type, int line, int column, Object message, Object... args) {
        report(stage, type, line, column, message, args);
        throw new PanicException();
    }

    private static String toEnumString(Enum<?> entry) {
        var lower = entry.name().toLowerCase().replaceAll("_", " ");
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }

    public enum Stage {
        SCAN,
        PARSE,
        EXECUTE;

        @Override
        public String toString() {
            return toEnumString(this);
        }
    }

    public enum Type {
        INVALID_CHARACTER,
        INVALID_TOKEN,
        INVALID_TYPE,
        REDECLARATION,
        UNDECLARED_ACCESS;

        @Override
        public String toString() {
            return toEnumString(this);
        }
    }
}
