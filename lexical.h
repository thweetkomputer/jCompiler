#include <ctype.h>
int is_nonzero_digit (char);
int is_octal_digit (char);
int is_hexadecimal_digit (char);
int is_nondigit (char);
int is_unary_op (char);
int is_empty(char);

struct on
{
  int is_num;  /*0 -> char, 1 -> num, 2 -> num or exp, 3 -> var with name */
  int cindex;
  union {char c; int n; char* name;} val;
  char name[30];
};

enum symbol
{
  EOFF,
  IDENT,
  FUNC_TYPE, /* int, void */
  MARK, /* [ ] ( ) { } ; ... */
  DECIMAL_CONST,
  OCTAL_CONST,
  HEXADECIMAL_CONST,
  CONST,
  IF, ELSE, WHILE, BREAK, CONTINUE, RETURN,
  UNARY_OP, INT
};

enum exp_symbol
{
  CONST_VAR, VAR
};
