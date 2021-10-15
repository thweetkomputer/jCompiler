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
  IF, ELSE, WHILE, BREAK, CONTINUE, RETURN
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

void error ();
int parse_func_type ();
int parse_break ();
int parse_if ();
int parse_else ();
int parse_while ();
int parse_continue ();
int parse_return ();

void parse_number ();
void parse_stmt (char **);
void parse_block (char **);
void parse_func_def (struct function * func);
void parse_comp_unit (struct function * func);
int main (int argc, char *argv[])
{
  token = (char *) malloc (1005 * sizeof (char) + 5);
  token_length = 0;
  token_capacity = 1000;
  // int fd = open (argv[argc - 1], O_RDONLY);
  // dup2 (fd, 0);

  /* input and output */
  getsym ();
  struct function *fp = (struct function *) malloc (sizeof (struct function));
  parse_comp_unit (fp); 
  getsym ();
  if (symbol != EOFF)
    error ();
  printf ("define %s %s @%s(){\n\t%s\n}", fp->func_type, fp->return_type, fp->name, fp->content);
   

  return 0;
}

void getsym()
{
  clear_token ();
  _getchar ();
  while (ch == ' ' || ch == '\n' || ch == '\t')
    _getchar ();
  if (ch == EOF)
  {
    symbol = EOFF;
    return;
  }
  if (isalpha (ch))
  {
    while (isalpha (ch) || isdigit (ch))
    {
      cat_token (ch);
      _getchar ();
    }
    ungetc (ch, stdin);
    if (parse_func_type () != -1)
    {
      symbol = FUNC_TYPE;
      return;
    }
    if (!parse_if ())
    {
      symbol = IF;
      return;
    }
    if (!parse_else ())
    {
      symbol = ELSE;
      return;
    }
    if (!parse_while ())
    {
      symbol = WHILE;
      return;
    }
    if (!parse_break ())
    {
      symbol = BREAK;
      return;
    }
    if (!parse_continue ())
    {
      symbol = CONTINUE;
      return;
    }
    if (!parse_return ())
    {
      symbol = RETURN;
      return;
    }
    symbol = IDENT;
    return;
  }
  /* number */
  if (isdigit (ch))
  {
    if (ch == '0')
    {
      cat_token (ch);
      _getchar ();
      if (is_octal_digit (ch)) {
        cat_token (ch);
        symbol = OCTAL_CONST;
        _getchar ();
        while (is_octal_digit (ch)) 
        {
          cat_token (ch);
          _getchar ();
        }
        if (ch == '9') 
          error ();
        ungetc (ch, stdin);
        long long ans = 0;
        char *sp = token;
        while (*sp) {
          ans = ans * 8 + *sp - '0';
          sp++;
        }
        if (ans >= 2147483647ll)
          error ();
        number = (int) ans;
        return;
      }
      if (ch == 'x' || ch == 'X')
      {
        cat_token (ch);
        symbol = HEXADECIMAL_CONST;
        char next_ch = getchar ();
        if (!is_hexadecimal_digit (next_ch)) 
        {
          ungetc (ch, stdin);
          ungetc (next_ch, stdin);
          symbol = DECIMAL_CONST;
          return;
        }
        ch = next_ch;
        while (is_hexadecimal_digit (ch))
        {
          cat_token (ch);
          _getchar ();
        }
        ungetc (ch, stdin);
        long long ans = 0;
        char *sp = token + 2;
        while (*sp)
        {
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
          error ();
        number = (int) ans;
        return;
      }
      symbol = DECIMAL_CONST;
      ungetc (ch, stdin);
      return;
    }
    symbol = DECIMAL_CONST;
    while (isdigit (ch))
    {
      cat_token (ch);
      _getchar ();
    }
    char *sp = token;
    long long ans = 0;
    while (*sp)
    {
      ans = ans * 10 + *sp - '0';
      sp++;
    }
    if (ans >= 2147483647ll)
      error ();
    ungetc (ch, stdin);
    number = (int) ans;
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
    error ();
  func->func_type = "dso_local";
  if (!strcmp (token, "int"))
    func->return_type = "i32";
  else
    func->return_type = "void";
  getsym ();
  if (symbol != IDENT)
    error ();
  func->name = (char *) malloc (strlen (token) + 1);
  strcpy (func->name, token);
  getsym ();
  if (strcmp (token, "("))
    error ();
  getsym ();
  if (strcmp (token, ")"))
    error ();
  getsym ();
  parse_block (&(func->content));
}

void parse_block (char **content) 
{
  if (strcmp (token, "{"))
    error ();
  getsym ();
  parse_stmt (content);
  getsym ();
  if (strcmp (token, "}"))
    error ();
}

void parse_stmt (char **content)
{
  if (symbol != RETURN)
    error ();
  *content = (char *)malloc(sizeof(char) * 100);
  (*content)[0] = '\0';
  strcpy (*content, "ret i32 ");
  getsym ();
  parse_number ();
  sprintf (*content + strlen (*content), "%d", number);
  getsym ();
  if (strcmp (token, ";"))
    error ();
}

void parse_number ()
{
  if (symbol == DECIMAL_CONST || symbol == HEXADECIMAL_CONST || symbol == OCTAL_CONST)
    return;
  error ();
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

void error ()
{
  exit (-1);
}

void _getchar ()
{
  ch = getchar ();
  putchar (ch);
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
    else if (next_ch == '*')
    {
      while (1)
      {
        next_ch = getchar ();
        if (next_ch == EOF)
        {
          ch = EOF;
          return;
        }
        if (next_ch == '*')
        {
          char nnext_ch = getchar ();
          if (nnext_ch == EOF)
          {
            ch = EOF;
            return;
          }
          if (nnext_ch == '/')
          {
            ch = getchar ();
            return;
          }
          ungetc (nnext_ch, stdin);
        }
      }
    }
    else
    {
      ungetc (next_ch, stdin);
    }
  }
}
