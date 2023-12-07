grammar FLanguage;

// Lexer rules
WS: [ \t\r\n]+ -> skip;
Integer: ('+' | '-')? [0-9]+;
Real: ('+' | '-')? [0-9]+ '.' [0-9]+;
Boolean: 'true'|'false';
LPAREN: '(';
RPAREN: ')';
QUOTE: '\'';
SETQ: 'setq';
FUNC: 'func';
LAMBDA: 'lambda';
PROG: 'prog';
COND: 'cond';
WHILE: 'while';
RETURN: 'return';
BREAK: 'break';
HEAD: 'head';
TAIL: 'tail';
CONS: 'cons';
EQUAL: 'equal';
NONEQUAL: 'nonequal';
LESS: 'less';
LESSEQ: 'lesseq';
GREATER: 'greater';
GREATEREQ: 'greatereq';
ISINT: 'isint';
ISREAL: 'isreal';
ISBOOL: 'isbool';
ISNULL: 'isnull';
ISATOM: 'isatom';
ISLIST: 'islist';
AND: 'and';
OR: 'or';
XOR: 'xor';
NOT: 'not';
EVAL: 'eval';
COMMA: ',';
PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
MOD: '%';
NULL: 'null';
Identifier: [A-Za-z]+;

// Parser rules
program: element+ EOF;

element: literal
       | funcCall
       | expression
       | specialForm
       | list
       | atom;

atom: identifier;

literal: Integer
        | Real
        | Boolean
        | NULL;

list: LPAREN element+ RPAREN;

specialForm: quote
           | setq
           | func
           | lambdaCall
           | lambda
           | evalCallUnnamed
           | evalCallNamed
           | prog
           | cond
           | whileForm
           | returnForm
           | breakForm;

quote: QUOTE element;

setq: LPAREN SETQ identifier element RPAREN;

func: LPAREN FUNC identifier LPAREN identifierList RPAREN element+ RPAREN;

lambda: LPAREN LAMBDA LPAREN identifierList RPAREN element+ RPAREN;

prog: LPAREN PROG LPAREN identifierList RPAREN element+ RPAREN;

identifierList : identifier (COMMA identifier)*;

argumentList : element (COMMA element)*;

identifier: Identifier;

cond: LPAREN COND element element element RPAREN;

whileForm: WHILE element element;

returnForm: RETURN element;

breakForm: BREAK;

funcCall: LPAREN identifier LPAREN argumentList RPAREN RPAREN;

lambdaCall: LPAREN lambda LPAREN argumentList RPAREN RPAREN;

expression: logicalExpression
       | relationalExpression
       | additiveExpression
       | multiplicativeExpression
       | comparisonExpression;

logicalExpression: (AND | OR | XOR) element element;

relationalExpression: comparisonOperator element element;

additiveExpression: (PLUS | MINUS) element element;

multiplicativeExpression: (MULT | DIV | MOD) element element;

comparisonExpression: LPAREN comparisonOperator (atom | literal) (atom | literal) RPAREN;
comparisonOperator: LESS | LESSEQ | GREATER | GREATEREQ | EQUAL | NONEQUAL;

evalCallUnnamed: LPAREN EVAL quote RPAREN;

evalCallNamed: LPAREN EVAL identifier RPAREN;


primary: quote
       | comparisonExpression
       | funcCall
       | evalCallUnnamed
       | evalCallNamed
       | atom
       | LPAREN expression RPAREN;
