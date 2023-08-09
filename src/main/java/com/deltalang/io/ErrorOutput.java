package com.deltalang.io;

public class ErrorOutput implements OutputStream {
    @Override
    public void write(Object x) {
        System.err.print(x);
    }

    @Override
    public void writef(String format, Object... args) {
        System.err.printf(format, args);
    }
}
