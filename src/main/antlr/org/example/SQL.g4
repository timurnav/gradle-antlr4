grammar SQL;

@header {
    package org.example;
}

sqlQuery
    : simpleSelect EOF;

simpleSelect
    : SELECT (allColumns | specificColumns) FROM table EOQ;

specificColumns
    : column (',' column)*
    ;

allColumns
    : ASTERISK
    ;

column
    : VALID_NAME
    ;
table
    : VALID_NAME
    ;

ASTERISK
    : '*'
    ;

SELECT: 'SELECT';
FROM: 'FROM';
EOQ: ';';

VALID_NAME
    : [a-zA-Z] [a-zA-Z_0-9]*
    ;

SPACE:          [ \t\r\n]+    -> channel(HIDDEN);
COMMENT_INPUT:  '/*' .*? '*/' -> channel(HIDDEN);
LINE_COMMENT:   ('-- ' | '#')
                ~[\r\n]*
                ('\r'? '\n' | EOF)
                              -> channel(HIDDEN);