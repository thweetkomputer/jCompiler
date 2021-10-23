#include <ctype.h>
int is_nonzero_digit (char);
int is_octal_digit (char);
int is_hexadecimal_digit (char);
int is_nondigit (char);
int is_unary_op (char);

struct on
{
  int is_num;
  union {char c; int n;} val;
};
