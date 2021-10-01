#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <ctype.h>
#include <stdlib.h>

void double_token ();
void cat_token (char);
void clear_token ();
void getsym ();
int reserver_keyword ();
int reserver_op ();
int reserver_op2 ();
void error ();

int keyword_count = 6;
char *KEYWORDS[] = {
  "if", "else", "while", "break", "continue", "return" 
};
char *KEYWORDS_O[] = {
  "If", "Else", "While", "Break", "Continue", "Return"
};

int operation_count = 12;
char *OPERATIONS[] = {
  "=", ";", "(", ")", "{", "}", "+", "*", "/", "<", ">", "=="
};
char *OPERATIONS_O[] = {
  "Assign", "Semicolon", "LPar", "RPar", "LBrace", "RBrace", "Plus", "Mult",
  "Div", "Lt", "Gt", "Eq"
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
  token = (char *) malloc (1005 * sizeof (char) + 5);
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
    if (token_length == 0) 
      continue;
    switch (symbol)
    {
      case IDENT:
        printf ("Ident(%s)\n", token);
        break;
      case NUMBER:
        printf ("Number(%s)\n", token);
        break;
      case KEYWORD:
        printf ("%s\n", KEYWORDS_O[reserver_keyword ()]);
        break;
      case OPERATION:
        printf ("%s\n", OPERATIONS_O[reserver_op2 ()]);
        break;
    }
    
  }
   

  return 0;
}

void getsym()
{
  clear_token ();
  while (ch == ' ' || ch == '\n' || ch == '\t')
    ch = getchar ();
  if (ch == EOF)
  {
    return;
  }
  /* ident or keyword */
  if (isalpha (ch) || ch == '_')
  {
    while (isalpha (ch) || isdigit (ch) || ch == '_')
    {
      cat_token (ch);
      ch = getchar ();
    }
    ungetc (ch, stdin);
    if (reserver_keyword () != -1)
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
    ungetc (ch, stdin);
    symbol = NUMBER;
    return;
  }
  /* operation */
  int index;
  if ((index = reserver_op ()) != -1)
  {
    cat_token (ch);
    symbol = OPERATION;
    char next = getchar ();
    if (next == '=')
    {
      cat_token (next);
      return;
    }
    ungetc (next, stdin);
    return;
  }
  error ();
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
    if (!strcmp (KEYWORDS[i], token))
      return i;
  return -1;
}

int reserver_op ()
{
  for (int i = 0; i < operation_count; i++)
    if (ch == OPERATIONS[i][0])
      return i;
  return -1;
}

int reserver_op2 ()
{
  for (int i = 0; i < operation_count; i++)
    if (!strcmp (OPERATIONS[i], token))
      return i;
  return -1;
}

void error ()
{
  printf ("ERR\n");
  exit (0);
}
