#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "lexical.h"

int flag;
int number;
enum symbol symbol;
enum exp_symbol exp_symbol;
int ch;
char *token;
int token_capacity;
int token_length;

int vn = 1, cvn = 1;
struct entry
{
  char name[1000];
  int n;
  int index;
  int cindex;
}vmap[1000], cmap[1000];
int vmapsp = 1, cmapsp = 1;
int get_var_index(char*);
int get_const_index_by_value(int);

struct function
{
  char *func_type;
  char *return_type;
  char *name;
  char *content;
};

void add_const_name();
void add_const_value();
int add_bold_const_value(int);
void add_var_name();

int getachar();
void _getchar();
void double_token();
void cat_token(char);
void clear_token();
void getsym();

void error(char *);
int parse_func_type();
int parse_break();
int parse_if();
int parse_else();
int parse_while();
int parse_continue();
int parse_return();

void print_const();
void print_stack();
void print_var();

// void parse_stmt (char **);
// void parse_block (char **);
int parse_func_def();
int parse_comp_unit();
int parse_number();
int parse_exp();
int parse_const_exp();
int parse_add_exp();
int parse_mul_exp();
int parse_unary_exp();
int parse_primary_exp();

int parse_const();
int parse_decl();
int parse_const_decl();
int parse_var_decl();
int parse_b_type();
int parse_const_def();
int parse_const_init_val();
int parse_var_decl();
int parse_var_def();
int parse_stmt ();

int parse_init_val();

int parse_block();
int parse_block_item();

int parse_l_val();

int op_cmp(struct on s1, struct on s2);
int print_num_and_op();
int cal_exp();

void stack_add_char(char);
void stack_add_int(int);
void stack_add_var();

int token_is_const(char*);
int token_is_var(char*);

struct on stack[100];
int sp = 1;
int main (int argc, char *argv[])
{
  stack[0].is_num = 0;
  stack[0].val.c = '#';
  token = (char *) malloc (1005 * sizeof (char) + 5);
  token_length = 0;
  token_capacity = 1000;
  getsym ();
  parse_comp_unit (); 
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
  char l1[100] = {};
  char num_and_op[100] = {};
  for (int i = 0; i < sp; i++) {
    if (stack[i].is_num)
      sprintf(num_and_op + strlen(num_and_op), "%d", stack[i].val.n);
    else
      sprintf(num_and_op + strlen(num_and_op), "%c", stack[i].val.c);
  }
  print_const();
  print_var();


  return 0;
}

int
op_cmp(struct on s1, struct on s2)
{
  char ma[][9] = {
    { 1,  1, -1, -1, -1, -1,  1, -1,  1},
    { 1,  1, -1, -1, -1, -1,  1, -1,  1},
    { 1,  1,  1,  1,  1, -1,  1, -1,  1}, 
    { 1,  1,  1,  1,  1, -1,  1, -1,  1}, 
    { 1,  1,  1,  1,  1, -1,  1, -1,  1}, 
    {-1, -1, -1, -1, -1, -1,  0, -1,  0},
    { 1,  1,  1,  1,  1,  1,  1,  1,  1},
    { 1,  1,  1,  1,  1,  1,  1,  1,  1},
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

void
print_stack()
{
#ifdef DEBUG
  printf("\n---cal-begin---\nstack is:\n");
  for (int i = 0; i < sp; i++) {
    if (stack[i].is_num == 1)
      printf("%d ", stack[i].val.n);
    else if (stack[i].is_num == 2)
      printf("%%%d ", stack[i].cindex);
    else
      printf("%c ", stack[i].val.c);
  }
  printf("\n");
#endif
}

int
cal_exp()
{
  stack_add_char('#');
  print_stack();
  int _sp = 0;
  struct on _stack[100];
  for (int i = 0; i < sp; i++) {
  /*  for (int i = 0; i < _sp; i++) {
    if (_stack[i].is_num == 1)
      printf("%d ", _stack[i].val.n);
    else if (_stack[i].is_num == 2)
      printf("%%x%d ", _stack[i].val.n);
    else
      printf("%c ", _stack[i].val.c);
  }
  printf("\n");
  */
    if (_sp == 0) {
      _stack[_sp++] = stack[i];
      continue;
    }
    if (_stack[_sp-1].is_num == 0 && stack[i].is_num == 0) {
      _stack[_sp++] = stack[i];
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
        int _res = 0;
        switch (op.val.c) {
          case '+':
            _res = left.val.n + right.val.n;
            break;
          case '-':
            _res = left.val.n - right.val.n;
            break;
          case '*':
            _res = left.val.n * right.val.n;
            break;
          case '/':
            _res = left.val.n / right.val.n;
            break;
          case '%':
            _res = left.val.n % right.val.n;
            break;
        }
        struct on r;
        r.is_num = 2; r.val.n = _res;
        _stack[_sp++] = r;
      }
      i--;
    } else if (j < 0) {
        if (stack[i].is_num) {
          stack[i].is_num = 2;
          // printf("\t%%x%d = add i32 0, %d\n", vn++, stack[i].val.n);
          // stack[i].val.n = vn-1;
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

int
print_num_and_op()
{
  stack_add_char('#');
  print_stack();
  int _sp = 0;
  struct on _stack[100];
  for (int i = 0; i < sp; i++) {
    if (_sp == 0) {
      _stack[_sp++] = stack[i];
      continue;
    }
    if (_stack[_sp-1].is_num == 0 && stack[i].is_num == 0) {
      _stack[_sp++] = stack[i];
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
        printf("\t%%%d = ", cvn);
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
        printf(" i32 %s%d, %%%d\n", left.is_num!=1?"%":"", left.is_num!=1?left.cindex:0, right.cindex);
        struct on r;
        r.is_num = 2; r.cindex = cvn++; vn++;
        _stack[_sp++] = r;
      }
      i--;
    } else if (j < 0) {
        if (stack[i].is_num == 1) {
          stack[i].is_num = 2;
          int _n = stack[i].val.n;
//          int _index = add_bold_const_value(_n);
          printf("\t%%%d = add i32 0, %d\n", cvn, _n);
          stack[i].cindex = cvn++; vn++;
        } else if (stack[i].is_num == 2) {
          if (stack[i].val.n > 0) {
            if (stack[i].cindex) 
              printf("\t%%%d = add i32 0, %%%d\n", cvn, stack[i].cindex);
            else
              printf("\t%%%d = add i32 0, %d\n", cvn, cmap[stack[i].val.n].n);
          } else {
            printf("\t%%%d = load i32, i32* %%%d\n", cvn, stack[i].cindex);
          }
          stack[i].cindex = cvn;
          cvn++;vn++;
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
#ifdef DEBUG
  printf("---return-%d---\n", _stack[1].cindex);
#endif
  return _stack[1].cindex;
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
  if (is_nondigit(ch)) {
    while (is_nondigit(ch) || isdigit (ch)) {
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
    if (!parse_const()) {
      symbol = CONST;
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
      number = 0;
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

int
parse_block()
{
  if (strcmp(token, "{"))
    error("parse_block->{");
  printf("{\n");
  while (1) {
    char _c = getachar();
    if (_c == EOF) 
      error("block missing }");
    if (_c == '}') {
      printf("}\n");
      return 0;
    }
    ungetc(_c, stdin);
    getsym();
    parse_block_item();
  }
  return 0;
}

int
parse_block_item()
{
  if (!strcmp(token, "int") || !strcmp(token, "const"))
    parse_decl();
  else
    parse_stmt();
  return 0;
}

int
parse_comp_unit ()
{
  printf("define ");
  parse_func_def();
}

int
parse_decl()
{
  if (symbol == CONST)
    parse_const_decl();
  else
    parse_var_decl();
  return 0;
}

int
parse_l_val()
{
  if (symbol != IDENT)
    error("l_val->IDENT");
  return 0;
}

int
parse_const_decl()
{
  exp_symbol = CONST_VAR;
  getsym();
  parse_b_type();
  getsym();
  parse_const_def();
  while (1) {
    char _c = getachar();
    if (_c == EOF) {
      ungetc(_c, stdin);
      return 0;
    }
    if (_c == ','){
      getsym();
      parse_const_def();
    } else {
      ungetc(_c, stdin);
      break;
    }
  }
  getsym();
  if (strcmp(token, ";")) {
    error("ConstDecl->;");
  }
  return 0;
}

int
parse_b_type()
{
  if (strcmp(token, "int"))
    error("Btype->INT");
  return 0;
}

void add_const_name()
{
  strcpy(cmap[cmapsp].name, token); 
  cmap[cmapsp].index = vn;
}

void add_const_value()
{
  cmap[cmapsp++].n = number;
  printf("%d\n", number);
  vn++;
}

void add_var_name()
{
  strcpy(vmap[vmapsp].name, token);
  vmap[vmapsp].index = vn;
  vmap[vmapsp].cindex = cvn;
  printf("\t%%%d = alloca i32\n", cvn);
  vn++;
  cvn++;
  vmapsp++;
}

int add_bold_const_value(int i)
{
  cmap[cmapsp].n = i;
  cmap[cmapsp].index = vn++;
  cmapsp++;
  return cmapsp-1;
}

int
token_is_const(char *str)
{
  for (int i = 0; i < cmapsp; i++)
    if (!strcmp(str, cmap[i].name))
      return 1;
  return 0;
}

int
token_is_var(char *str)
{
  for (int i = 0; i < vmapsp; i++)
    if (!strcmp(str, vmap[i].name))
      return 1;
  return 0;
}

int
parse_const_def()
{
  if (symbol != IDENT) {
    error("parse_const_def IDENT");
  }
  add_const_name();
  getsym();
  if (strcmp(token, "=")) {
    error("const var undefined");
  }
  getsym();
  parse_const_init_val();
  print_const();
  return 0;
}

void
print_var()
{
#ifdef DEBUG
  printf("====var====\n");
  for (int i = 1; i < vmapsp; i++)
    printf("%%%d: %s=%d\n", vmap[i].cindex, strlen(vmap[i].name) ? vmap[i].name : "NULL", vmap[i].n);
  printf("===========\n\n");
#endif
}


void
print_const()
{
#ifdef DEBUG
  printf("===const===\n");
  for (int i = 1; i < cmapsp; i++)
    printf("%%%d: %s=%d\n", cmap[i].cindex, strlen(cmap[i].name) ? cmap[i].name : "NULL", cmap[i].n);
  printf("===========\n\n");
#endif
}

int
parse_const_init_val()
{
  sp = 1;
  parse_const_exp();
  int number = cal_exp();
  add_const_value();
  return 0;
}

int
parse_const_exp()
{
  parse_add_exp();
  return 0;
}

int
parse_var_decl()
{
  exp_symbol = VAR;
  if (strcmp(token, "int")) 
    error("parse_val_decl->int");
  getsym();
  parse_var_def();
  while (1) {
    char _c = getachar();
    if (_c == EOF) {
      ungetc(_c, stdin);
      return 0;
    }
    if (_c == ','){
      getsym();
      parse_var_def();
    } else {
      ungetc(_c, stdin);
      break;
    }
  }
  getsym();
  if (strcmp(token, ";")) {
    error("ValDecl->;");
  }
  return 0;
}

int parse_var_def()
{
  if (symbol != IDENT)
    error("parse_var_def->IDENT");
  if (token_is_var(token) || token_is_const(token)) {
    error("duplicate var");
  }
  int _vn = cvn;
  add_var_name();
  char _c = getachar();
  if (_c != '=') {
    ungetc(_c, stdin);
    return 0;
  }
  getsym();
  int _index = parse_init_val();
  printf("\tstore %%%d, i32* %%%d\n", _index, _vn);
  print_var();
  return 0;
}

int
parse_init_val()
{
  sp = 1;
  parse_exp();
  return print_num_and_op();
}

int
parse_func_def()
{
  if (symbol != FUNC_TYPE)
    error(NULL);
  printf("dso_local ");
  if (!strcmp (token, "int"))
    // func->return_type = "i32";
    printf("i32 ");
  getsym();
  if (symbol != IDENT)
    error(NULL);
  printf("@%s", token);
  getsym();
  if (strcmp (token, "("))
    error(NULL);
  printf("(");
  getsym();
  if (strcmp (token, ")"))
    error(NULL);
  printf(")");
  getsym();
  parse_block();
  return 0;
}

int parse_stmt (char **content)
{
  if (!strcmp(";", token))
    return 0;
  if (symbol == RETURN) {
    getsym();
    sp = 1;
    parse_exp();
    int _index = print_num_and_op();
    printf("\tret i32 %%%d\n", _index);
    getsym();
    if (strcmp(";", token))
      error("stmt return;");
    return 0;
  }
  char _c = getachar();
  if (_c == '=') {
    if (token_is_const(token)) {
      error("cannot modify const");
    }
    int _i = get_var_index(token);
    if (!_i) {
      printf("%s ", token);
      error("not defined");
    }
    getsym();
    sp = 1;
    parse_exp();
    int _index = print_num_and_op();
    printf("\tstore i32 %%%d, i32* %%%d\n", _index, -_i);
  } else if (symbol == IDENT){
    ungetc(_c, stdin);
    parse_exp();
  } else {
    error("parse_stmt");
  }
  getsym ();
  if (strcmp (token, ";"))
    error(";");
  return 0;
}

int parse_number ()
{
  if (symbol == DECIMAL_CONST || symbol == HEXADECIMAL_CONST || symbol == OCTAL_CONST)
    return 1;
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
int parse_const ()
{
  return strcmp (token, "const");
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
  // putchar(ch);
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
  parse_add_exp();
  return 0;
}

int
parse_add_exp()
{
  parse_mul_exp();
  while (1) {
    int _c = getachar();
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
    int _c = getachar();
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
  // printf("\nstr=%s\n", token);
  if (!strcmp(token, "(")) {
    stack_add_char('(');
    getsym();
    parse_exp();
    getsym();
    if (strcmp(token, ")"))
      error(")");
    stack_add_char(')');
  } else if (symbol == DECIMAL_CONST || symbol == HEXADECIMAL_CONST || symbol == OCTAL_CONST ){
    stack_add_int(number);
  } else if (symbol == IDENT) {
    stack_add_var(token);
  } else {
    error ("parse_primary_exp");
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
void
stack_add_var(char *str)
{
  print_const();
  print_var();
  stack[sp].is_num = 2;
  int index = get_var_index(str);
  if (!index)
    error("var not found.");
  stack[sp].val.n = index;
  if (index < 0)
    stack[sp].cindex = vmap[-index].cindex;
  sp++;
}
int
get_var_index(char *str)
{
  for (int i = 0; i < cmapsp; i++) 
    if (!strcmp(str, cmap[i].name))
      return i;
  for (int i = 0; i < vmapsp; i++)
    if (!strcmp(str, vmap[i].name))
      return -i;
  return 0;
}
int
getachar()
{
  char _c = getchar();
  while (is_empty(_c))
    _c = getchar();
  return _c;
}

int
get_const_index_by_value(int i)
{
  for (int i = 0; i < cmapsp; i++)
    if (cmap[i].n == i)
      return i;
  return -1;
}
