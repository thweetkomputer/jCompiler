#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include "lexical.h"

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
  UNARY_OP
};

int flag;
int number;
enum symbol symbol;
int ch;
char *token;
int token_capacity;
int token_length;

struct function
{
  char *func_type;
  char *return_type;
  char *name;
  char *content;
};

void _getchar ();
void double_token ();
void cat_token (char);
void clear_token ();
void getsym ();

void error (char *);
int parse_func_type ();
int parse_break ();
int parse_if ();
int parse_else ();
int parse_while ();
int parse_continue ();
int parse_return ();

void parse_stmt (char **);
void parse_block (char **);
void parse_func_def (struct function * func);
void parse_comp_unit (struct function * func);
void parse_number ();
int parse_exp ();
int parse_add_exp ();
int parse_mul_exp ();
int parse_unary_exp ();
int parse_primary_exp ();

void stack_add_char(char);
void stack_add_int(int);

struct on stack[100];
int sp;
int main (int argc, char *argv[])
{
  token = (char *) malloc (1005 * sizeof (char) + 5);
  token_length = 0;
  token_capacity = 1000;
  getsym ();
  struct function *fp = (struct function *) malloc (sizeof (struct function));
  parse_comp_unit (fp); 
  getsym ();
  if (symbol != EOFF)
    error(NULL);
   
  for (int i = 0; i < sp; i++) {
    if (stack[i].is_num) 
      printf("%d", stack[i].val.n);
    else
      putchar(stack[i].val.c);
  }

  return 0;
}

void getsym()
{
  clear_token ();
  _getchar ();
  while (is_empty(ch))
    _getchar ();
  if (ch == EOF) {
    symbol = EOFF;
    return;
  }
  if (isalpha (ch)) {
    while (isalpha (ch) || isdigit (ch)) {
      cat_token (ch);
      _getchar ();
    }
    ungetc (ch, stdin);
    if (parse_func_type () != -1) {
      symbol = FUNC_TYPE;
      return;
    }
    if (!parse_if ()) {
      symbol = IF;
      return;
    }
    if (!parse_else ()) {
      symbol = ELSE;
      return;
    }
    if (!parse_while ()) {
      symbol = WHILE;
      return;
    }
    if (!parse_break ()) {
      symbol = BREAK;
      return;
    }
    if (!parse_continue ()) {
      symbol = CONTINUE;
      return;
    }
    if (!parse_return ()) {
      symbol = RETURN;
      return;
    }
    symbol = IDENT;
    return;
  }
  /* number */
  if (isdigit (ch)) {
    if (ch == '0') {
      cat_token (ch);
      _getchar ();
      if (is_octal_digit (ch)) {
        cat_token (ch);
        symbol = OCTAL_CONST;
        _getchar ();
        while (is_octal_digit (ch)) {
          cat_token (ch);
          _getchar ();
        }
        if (ch == '9') 
          error(NULL);
        ungetc (ch, stdin);
        long long ans = 0;
        char *sp = token;
        while (*sp) {
          ans = ans * 8 + *sp - '0';
          sp++;
        }
        if (ans >= 2147483647ll)
          error(NULL);
        number = (int) ans;
        return;
      }
      if (ch == 'x' || ch == 'X') {
        cat_token (ch);
        symbol = HEXADECIMAL_CONST;
        char next_ch = getchar ();
        if (!is_hexadecimal_digit (next_ch)) {
          ungetc (ch, stdin);
          ungetc (next_ch, stdin);
          symbol = DECIMAL_CONST;
          return;
        }
        ch = next_ch;
        while (is_hexadecimal_digit (ch)) {
          cat_token (ch);
          _getchar ();
        }
        ungetc (ch, stdin);
        long long ans = 0;
        char *sp = token + 2;
        while (*sp) {
          ans *= 16;
          char c = *sp;
          if (isdigit (c))
            ans += c - '0';
          else if (c >= 'a' && c <= 'f')
            ans += c - 'a' + 10;
          else
            ans += c - 'A' + 10;
          sp++;
        }
        if (ans >= 2147483647ll)
          error(NULL);
        number = (int) ans;
        return;
      }
      symbol = DECIMAL_CONST;
      ungetc (ch, stdin);
      return;
    }
    symbol = DECIMAL_CONST;
    while (isdigit (ch)) {
      cat_token (ch);
      _getchar ();
    }
    char *sp = token;
    long long ans = 0;
    while (*sp) {
      ans = ans * 10 + *sp - '0';
      sp++;
    }
    if (ans >= 2147483647ll)
      error(NULL);
    ungetc (ch, stdin);
    number = (int) ans;
    return;
  }
  if (is_unary_op(ch)) {
    symbol = UNARY_OP;
    cat_token(ch);
    return;
  }
  symbol = MARK;
  cat_token (ch);
}

void parse_comp_unit (struct function * func)
{
  parse_func_def (func);
}

void parse_func_def (struct function * func)
{
  if (symbol != FUNC_TYPE)
    error(NULL);
  func->func_type = "dso_local";
  if (!strcmp (token, "int"))
    func->return_type = "i32";
  else
    func->return_type = "void";
  getsym ();
  if (symbol != IDENT)
    error(NULL);
  func->name = (char *) malloc (strlen (token) + 1);
  strcpy (func->name, token);
  getsym ();
  if (strcmp (token, "("))
    error(NULL);
  getsym ();
  if (strcmp (token, ")"))
    error(NULL);
  getsym ();
  parse_block (&(func->content));
}

void parse_block (char **content) 
{
  if (strcmp (token, "{"))
    error("{");
  getsym ();
  parse_stmt (content);
  getsym ();
  if (strcmp (token, "}"))
    error("}");
}

void parse_stmt (char **content)
{
  if (symbol != RETURN)
    error("return");
  *content = (char *)malloc(sizeof(char) * 100);
  (*content)[0] = '\0';
  strcpy (*content, "ret i32 ");
  getsym ();
  int exp_val;
  parse_exp ();
  sprintf (*content + strlen (*content), "%d", number);
  getsym ();
  if (strcmp (token, ";"))
    error(";");
}

void parse_number ()
{
  if (symbol == DECIMAL_CONST || symbol == HEXADECIMAL_CONST || symbol == OCTAL_CONST)
    return;
  error("NUMBER");
}


int parse_func_type ()
{
  // if (!strcmp (token, "void"))
  //  return 0;
  if (!strcmp (token, "int"))
    return 0;
  return -1;
}
int parse_break ()
{
  return strcmp (token, "break");
}
int parse_if ()
{
  return strcmp (token, "if");
}
int parse_else ()
{
  return strcmp (token, "else");
}
int parse_while ()
{
  return strcmp (token, "while");
}
int parse_continue ()
{
  return strcmp (token, "continue");
}
int parse_return ()
{
  return strcmp (token, "return");
}

void double_token ()
{
  free (token);
  char *new_token = (char *) malloc (sizeof (char) * 2 * token_capacity + 5);
  strcpy (new_token, token);
  token = new_token;
}

void cat_token (char ch)
{
  if (token_length == token_capacity)
    double_token ();
  token[token_length++] = ch;
  token[token_length] = '\0';
}

void clear_token ()
{
  token_length = 0;
  token[token_length] = '\0';
}

void error (char *s)
{
  if (s) printf("\nexpect:%s\n", s);
  exit (-1);
}

void _getchar ()
{
  ch = getchar ();
  putchar(ch);
  if (ch == '/')
  {
    int next_ch = getchar ();
    if (next_ch == '/')
    {
      while (1)
      {
        next_ch = getchar ();
        if (next_ch == EOF)
        {
          ch = EOF;
          return;
        }
        if (next_ch == '\n')
        {
          ch = getchar ();
          return;
        }
      }
    }
    else if (next_ch == '*') {
      while (1) {
        next_ch = getchar ();
        if (next_ch == EOF) {
          ch = EOF;
          return;
        }
        if (next_ch == '*') {
          char nnext_ch = getchar ();
          if (nnext_ch == EOF) {
            ch = EOF;
            return;
          }
          if (nnext_ch == '/') {
            ch = getchar ();
            return;
          }
          ungetc (nnext_ch, stdin);
        }
      }
    }
    else {
      ungetc (next_ch, stdin);
    }
  }
}

int
parse_exp() {
  return parse_add_exp();
}

int
parse_add_exp()
{
  parse_mul_exp();
  while (1) {
    int _c = getchar();
    while (is_empty(_c)) {
      printf("\nget %c\n", _c);
      _c = getchar();
    }
    if (_c == EOF) {
      ungetc(_c, stdin);
      return 0;
    }
    putchar(_c);
    if (is_unary_op(_c)) {
      stack_add_char(_c);
      getsym();
      printf("mul");
      parse_mul_exp();
    } else { 
      ungetc(_c, stdin);
      break;
    } 
  } 
  return 0;
}

int
parse_mul_exp()
{
  parse_unary_exp();
  while (1) {
    int _c = getchar();
    while (is_empty(_c)) 
      _c = getchar();
    if (_c == EOF) {
      ungetc(_c, stdin);
      return 0;
    }
    if (_c == '*' || _c == '/' || _c == '%') {
      stack_add_char(_c);
      getsym();
      parse_unary_exp();
    } else {
      ungetc(_c, stdin);
      break;
    }
  }
  return 0;
}

int
parse_unary_exp()
{
  if (symbol == UNARY_OP) {
    stack_add_char(token[0]);
    getsym();
    parse_unary_exp();
  } else {
    parse_primary_exp();
  }
  return 0;
}

int
parse_primary_exp()
{
  if (!strcmp(token, "(")) {
    stack_add_char('(');
    getsym();
    printf("!");
    parse_exp();
    getsym();
    if (strcmp(token, ")"))
      error(")");
    stack_add_char(')');
  } else {
    parse_number();
    stack_add_int(number);
  }
  return 0;

}

void 
stack_add_char(char c)
{
  stack[sp].is_num = 0;
  stack[sp].val.c = c;
  sp++;
}
void 
stack_add_int(int i)
{
  stack[sp].is_num = 1;
  stack[sp].val.n = i;
  sp++;
}
