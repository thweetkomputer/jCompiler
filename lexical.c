#include "lexical.h"
int is_nonzero_digit (char ch)
{
  return isdigit (ch) && ch != '0';
}

int is_octal_digit (char ch)
{
  return ch >= '0' && ch <= '8';
}

int is_hexadecimal_digit (char ch)
{
    return isdigit (ch) || (ch <= 'F' && ch >= 'A') || (ch <= 'f' && ch >= 'a');
}

int is_nondigit (char ch)
{
  return isalpha (ch) && ch == '_';
}

int is_unary_op(char ch)
{
  return ch == '+' || ch == '-';
}
