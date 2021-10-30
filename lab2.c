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
int parse_primary_exp();


int op_cmp(struct on s1, struct on s2);
int print_num_and_op();

void stack_add_char(char);
void stack_add_int(int);

struct on stack[100];
int sp = 1;
int vn;
int main (int argc, char *argv[])
{
  int ccc;
  while ((ccc = getchar())!=EOF){
    if (is_empty(ccc))
      continue;
    putchar(ccc);
  }
  return 0;
  stack[0].is_num = 0;
  stack[0].val.c = '#';
  token = (char *) malloc (1005 * sizeof (char) + 5);
  token_length = 0;
  token_capacity = 1000;
  getsym ();
  struct function *fp = (struct function *) malloc (sizeof (struct function));
  parse_comp_unit (fp); 
  getsym ();
  if (symbol != EOFF)
    error(NULL);
  /*
  for (int i = 0; i < sp; i++) {
    if (stack[i].is_num) 
      printf("%d", stack[i].val.n);
    else
      putchar(stack[i].val.c);
  }
  */
  stack_add_char('#');
  char l1[100] = {};
  printf("define %s %s @%s(){", fp->func_type, fp->return_type, fp->name);
  char num_and_op[100] = {};
  for (int i = 0; i < sp; i++) {
    if (stack[i].is_num)
      sprintf(num_and_op + strlen(num_and_op), "%d", stack[i].val.n);
    else
      sprintf(num_and_op + strlen(num_and_op), "%c", stack[i].val.c);
  }

  int res = print_num_and_op();
  // int res = execl("/bin/echo", "python3", "cal.py", num_and_op, (char *)0);
  printf("\tret i32 %%x%d\n}", res);

  return 0;
}

int
op_cmp(struct on s1, struct on s2)
{
  char ma[][9] = {
    {-1, -1, -1, -1, -1, -1, 1, -1, 1},
    {-1, -1, -1, -1, -1, -1, 1, -1, 1},
    {1, 1, 1, 1, 1, -1, 1, -1, 1}, 
    {1, 1, 1, 1, 1, -1, 1, -1, 1}, 
    {1, 1, 1, 1, 1, -1, 1, -1, 1}, 
    {-1, -1, -1, -1, -1, -1, 0, -1, 0},
    {1, 1, 1, 1, 1, 1, 1, 1, 1},
    {1, 1, 1, 1, 1, 1, 1, 1, 1},
    {-1, -1, -1, -1, -1, -1, -1, -1, -1}
  };
  char cm[] = {'+', '-', '*', '/', '%', '(', ')', 'i', '#'};
  int i1, i2;
  if (s1.is_num == 1) i1 = 7;
  else {
    for (i1 = 0; i1 < 9; i1++)
      if (s1.val.c == cm[i1])
        break;
  }
  if (s2.is_num) i2 = 7;
  else {
    for (i2 = 0; i2 < 9; i2++)
      if (s2.val.c == cm[i2])
        break;
  }
  return ma[i1][i2];
}

int
print_num_and_op()
{
  int _sp = 0;
  struct on _stack[100];
  for (int i = 0; i < sp; i++) {
    if (_sp == 0) {
      _stack[_sp++] = stack[i];
      printf("\n");
      continue;
    }
    int rr = _sp-1;
    for (; rr >= 0; rr--) {
      if (_stack[rr].is_num != 2)
        break;
    }
    int j = op_cmp(_stack[rr], stack[i]);
    if (j > 0) {
      struct on op = _stack[_sp-2];
      struct on right = _stack[_sp-1];
      if (right.is_num) {
        struct on left;
        if (op.is_num == 0 && (op.val.c == '(' || op.val.c == '#')) {
          left.val.n = 0;
          left.is_num = 1;
          op.val.c = '+';
          _sp--;
        } else if (_sp >= 4 && _stack[_sp-3].is_num) {
          left = _stack[_sp-3];
          _sp -= 3;
        } else {
          left.is_num = 1;
          left.val.n = 0;
          _sp -= 2;
        }
        printf("\t%%x%d = ", vn);
        switch (op.val.c) {
          case '+':
            printf("add");
            break;
          case '-':
            printf("sub");
            break;
          case '*':
            printf("mul");
            break;
          case '/':
            printf("sdiv");
            break;
          case '%':
            printf("srem");
            break;
        }
        printf(" i32 %s%d, %s%d\n", left.is_num == 1 ? "":"\%x", left.val.n, right.is_num == 1 ? "":"\%x", right.val.n);
        struct on r;
        r.is_num = 2; r.val.n = vn++;
        _stack[_sp++] = r;
      }
      i--;
    } else if (j < 0) {
        if (stack[i].is_num) {
          stack[i].is_num = 2;
          printf("\t%%x%d = add i32 0, %d\n", vn++, stack[i].val.n);
          stack[i].val.n = vn-1;
        }
        _stack[_sp++] = stack[i];
    } else {
      _stack[_sp-2] = _stack[_sp-1];
      _sp--;
    }
    /* 
    for (int i = 0; i < _sp; i++) {
      if (_stack[i].is_num == 1)
        printf("%d ", _stack[i].val.n);
      else if (_stack[i].is_num == 2)
        printf("%%x%d ", _stack[i].val.n);
      else
        printf("%c ", _stack[i].val.c);
    }
      printf("\n");
    */
  }
  return _stack[1].val.n;
}

void
getsym()
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
      _c = getchar();
    }
    if (_c == EOF) {
      ungetc(_c, stdin);
      return 0;
    }
    if (is_unary_op(_c)) {
      stack_add_char(_c);
      getsym();
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
