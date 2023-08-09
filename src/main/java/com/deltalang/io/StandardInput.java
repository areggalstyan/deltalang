package com.deltalang.io;

import java.util.Scanner;

public class StandardInput implements InputStream {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        return scanner.next();
    }
}
