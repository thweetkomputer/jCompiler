#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <ctype.h>
#include <stdlib.h>

void double_token ();
void cat_token (char);
void clear_token ();
int getsym ();
int reserver ();

int keyword_count = 6;
char *KEYWORD[] = {
 "if", "else", "while", "break", "continue", "return" 
};

int operation_count = 12;
char *OPERATION[] = {
  "=", ";", "(", ")", "{", "}", "+", "*", "/", "<", ">", "=="
};

enum symbol
{
  IDENT, NUMBER, KEYWORD, OPERATION, ERR, UNDERLINE
};

enum symbol symbol;
int ch;
char *token;
int token_capacity;
int token_length;
int main (int argc, char *argv[])
{
  *token = (char *) malloc (1005 * sizeof (char) + 5);
  token_length = 0;
  token_capacity = 1000;
  /* IO redirection */
  if (argc > 1)
  {
    int fd = open (argv[argc - 1], O_RDONLY);
    if (fd == -1)
      return -1;
    dup2 (fd, 0);
  }
  // ungetc (ch, stdin);
  /* input and output */
  while ((ch = getchar ()) != EOF) 
  {
    getsym ();
    switch (symbol)
    {
      case IDENT:
        
    }
    
  }
   

  return 0;
}

int getsym()
{
  clear_token ();
  while (ch == ' ' || ch == '\n' || ch == '\t')
    ch = getchar ();
  if (ch == EOF)
  {
    return 0;
  }
  /* ident or keyword */
  if (isalpha (ch))
  {
    while (isalpha (ch) || isdigit (ch))
    {
      cat_token (ch);
      ch = getchar ();
    }
    ugetc (ch, stdin);
    if (reserver () != -1)
      symbol = KEYWORD;
    else
      symbol = IDENT;
    return;
  }
  /* number */
  if (isdigit (ch))
  {
    while (isdigit (ch))
    {
      cat_token (ch);
      ch = getchar ();
    }
    ugetc (ch, stdin);
    symbol = NUMBER;
    return;
  }
  /* operation */
  /* underline */
  if (ch == '_')
  {
    cat_token (ch);
    symbol = UNDERLINE;
    return;
  }
}

void double_token ()
{
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

int reserver_keyword ()
{
  for (int i = 0; i < keyword_count; i++)
    if (!strcmp (KEYWORD[i], token))
      return i;
  return -1;
}
