package com.deltalang.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OperationException extends RuntimeException {
    String message;
}
