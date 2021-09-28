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
bool reserver ();

enum symbol
{
  IDENT, NUMBER, KEYWORD, OPERATION, ERR
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
  while (1) 
  {
    ch = getchar ();
    getsym ();
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
  if (isalpha (ch))
  {
    while (isalpha (ch) || isdigit (ch))
    {
      cat_token (ch);
      ch = getchar ();_
    }
    ugetc (ch, stdin);
    if (reserver ())
      symbol = KEYWORD;
    else
      symbol = IDENT;
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
}

void clear_token ()
{
  token_length = 0;
}

void reserver ()
{
  
}
