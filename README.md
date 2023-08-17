# DeltaLang

DeltaLang is a simple interpreted programming language. 

## Usage

Pass the program name as a command line argument.

## Features

- Operators (+, -, *, /, %, ==, !=, >, >=, <, <=, !)
- Variables (boolean, number, string, nil)
- Implicit type casting
- Control flow (if/else, while/for, scopes)
- Functions (supports nested declarations, but without closures)
- Number parsing
- Input/Output
- Comments
- Descriptive error messages

## Example

```
// Input: a binary operator (+, -, *, /, ^), two numbers
// Output: the result of applying the operator to those numbers

def pow(a, b) {
    var c = 1;
    for var i = 0; i < b; i = i + 1; {
        c = c * a;
    }
    return c;
}

while true; {
    var operator = input();
    if operator == "+"; {
        print(parse(input()) + parse(input()) + "\n");
    } else if operator == "-"; {
        print(parse(input()) - parse(input()) + "\n");
    } else if operator == "*"; {
        print(parse(input()) * parse(input()) + "\n");
    } else if operator == "/"; {
        print(parse(input()) / parse(input()) + "\n");
    } else if operator == "^"; {
        print(pow(parse(input()), parse(input())) + "\n");
    } else {
        print("Invalid operator provided!\n");
    }
}
```

## Variables

```
var <name>;
var <name> = <value>;
```

## Control flow

```
if <condition>; {
    <body>
} else if <condition>; {
    <body>
} else {
    <body>
}

for <init>; <condition>; <increment>; {
    <body>
}

while <condition>; {
    <body>
}
```

## Functions

```
def <name>(<param_1>, <param_2>, ..., <param_n>) {
    <body>
}

return;

return <value>;
```

## Native functions

```
parse(<string>)

input()

print(<string/number/bool>)
```

## Comments

```
// <body>
```

## Error messages

```
[<phase> Error] <error message> at [<line>:<column>]: <fragment>
```
