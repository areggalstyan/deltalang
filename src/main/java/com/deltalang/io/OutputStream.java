package com.deltalang.io;

public interface OutputStream {
    void write(Object x);
    void writef(String format, Object... args);
}
