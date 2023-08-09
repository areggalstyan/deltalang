package com.deltalang.io;

public class StandardOutput implements OutputStream {
    @Override
    public void write(Object x) {
        System.out.print(x);
    }

    @Override
    public void writef(String format, Object... args) {
        System.out.printf(format, args);
    }
}
