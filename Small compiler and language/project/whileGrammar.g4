grammar whileGrammar;

program
    :codeblock
    ;

codeblock
    : statement+
    ;

statement
    : declaration SC
    | assignment SC
    | printcall SC
    | function
    | functioncall SC
    | control
    | rtrn
    ;

declaration
    : decs ID
    | decs assignment
    ;

assignment
    :ID ASS exp
    |ID ASS conditional
    ;

exp
    :pexp
    |operand
    |functioncall
    |exp EXPO exp
    |exp (DIV | MULT | MODULUS) exp
    |exp (ADD | SUB) exp
    ;

pexp
    :LPARA exp RPARA
    ;

operand
    : INT
    | SUB INT
    | FLOAT
    | SUB FLOAT
    | ID
    | BOOL
    ;

decs
    :DECINT
    |DECFLOAT
    |DECBOOL
    ;

control
    : ifst
    | whilest
    ;

ifst
    :IF LPARA conditional RPARA then
    |IF LPARA conditional RPARA then elsest
    ;

then
    :LBR codeblock RBR
    ;

elsest
    :ELSE LBR codeblock RBR
    ;

whilest
    :WHILE LPARA conditional RPARA LBR codeblock RBR
    ;

conditional
    : paraconditional
    | ID
    | BOOL
    | NOT conditional
    | conditional ( AND | OR ) conditional
    | exp (GTHAN | GTHANEQ | LTHAN | LTHANEQ) exp
    | conditional (EQUALITY | INEQUALITY) conditional
    | exp (EQUALITY | INEQUALITY) exp
    | functioncall
    ;

paraconditional
    : LPARA conditional RPARA
    ;

function
    : typefunction
    | voidfunction
    ;

typefunction
    : funcdeclaration LBR codeblock RBR
    ;

voidfunction
    : '$void' ID LPARA funcarguments RPARA LBR codeblock RBR
    ;

funcdeclaration
    :declaration LPARA funcarguments RPARA
    ;

funcarguments
    :(declaration ',') + declaration
    |declaration
    |
    ;

rtrn
    : 'Return' (exp | conditional) SC
    ;

functioncall
    : ID LPARA arguments? RPARA
    ;

arguments
    : arguments ',' arguments
    | conditional
    | exp
    |
    ;

printcall
    : 'print' LPARA (exp | conditional | STRING) RPARA
    ;

ASS : '=' ;
LBR : '{' ;
RBR : '}' ;
LPARA : '(' ;
RPARA : ')' ;
SC : ';' ;

//Control flows
IF : 'if' ;
ELSE : 'else' ;
WHILE : 'while' ;
DO : 'do' ;

//Operators
ADD : '+' ;
SUB : '-' ;
MULT : '*' ;
DIV : '/' ;
EXPO : '^' ;
MODULUS : '%' ;

//Conditional operators
LTHAN : '<' ;
GTHAN : '>' ;
LTHANEQ : '<=' ;
GTHANEQ : '>=' ;
EQUALITY : '==' ;
INEQUALITY : '!=' ;
AND : '&&' ;
OR : '||' ;
NOT : '!' ;

//Operands
FLOAT : INT FLTP [0-9]*;
ID : [a-z] [a-zA-Z0-9_-]*;
INT : [0-9]+;
FLTP : '.';
BOOL : 'True' | 'False' ;
STRING : '"' .*? '"';

//Declarations
DECINT : '$int' ;
DECFLOAT : '$float' ;
DECBOOL : '$boolean' ;

COMMENT : '/^' .*? '^/' -> skip;
WS   : [ \t\r\n]+ -> skip;