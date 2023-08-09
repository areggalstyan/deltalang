package com.deltalang.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenPosition {
    int line;
    int column;
}
