grammar Zcc;

IF: 'if';
ELSE: 'else';
VOID: 'void';
INT: 'int';
RETURN: 'return';
CONST: 'const';
IDENT: [_a-zA-Z] [_a-zA-Z0-9]*;
DECIMAL_CONST: [1-9] [0-9]*;
OCTAL_CONST: '0' [1-7]*;
HEXADECIMAL_CONST: ('0x' | '0X') [0-9a-zA-F]+;
WHITE_SPACE: [ \t\r\n] -> skip;
ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
MOD: '%';
NOT: '!';
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
LBRACK: '[';
RBRACK: ']';

GE: '>=';
LE: '<=';
GT: '>';
LT: '<';
COMMA: ',';
SEMECOLON: ';';
EQ: '==';
NE: '!=';
AND: '&&';
OR: '||';
ASSIGN: '=';

ident: IDENT;
number: DECIMAL_CONST | OCTAL_CONST | HEXADECIMAL_CONST;
compUnit: funcDef;
decl: constDecl | varDecl;
constDecl: CONST bType constDef ( COMMA constDef )* SEMECOLON;
bType: INT;
constDef: ident ASSIGN constInitVal;
constInitVal: constExp;
constExp: addExp;
varDef: ident | ident ASSIGN initVal;
varDecl: bType varDef ( COMMA varDef )* SEMECOLON;
cond: lOrExp;
initVal: exp;
funcDef: funcType ident LPAREN RPAREN block;
funcType: INT;
block: LBRACE blockItem* RBRACE;
blockItem: decl | stmt;
stmt: lVal ASSIGN exp SEMECOLON | exp? SEMECOLON | RETURN exp SEMECOLON | IF LPAREN cond RPAREN stmt (ELSE stmt) ? | block;
exp: addExp;
lVal: ident;
primaryExp: LPAREN exp RPAREN | lVal | number;
addExp: mulExp ( unaryOp mulExp ) *;
mulExp: unaryExp  ( pUnayOp unaryExp ) *;
relExp: addExp (cmpOp addExp) *;
eqExp: relExp (eOp relExp) *;
lAndExp: eqExp (AND eqExp) *;
lOrExp: lAndExp (OR lAndExp) *;
unaryExp: primaryExp | ident LPAREN funcRParams? RPAREN | unaryOp unaryExp;
unaryOp: ADD | SUB | NOT;
pUnayOp: MUL | DIV | MOD;
cmpOp: LT | GT | LE | GE;
eOp: EQ | NE;
funcRParams: exp ( COMMA exp )*;
