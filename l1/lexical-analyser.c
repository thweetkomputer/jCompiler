#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <ctype.h>
#include <stdlib.h>

void double_token ();
void cat_token (ch);
void clear_token ();

char *token;
int token_capacity;
int token_length;
int main (int argc, char *argv[])
{
  char *token = (char *) malloc (1005 * sizeof (char) + 5);
  int token_length = 0;
  int token_capacity = 1000;
  if (argc > 1)
  {
    int fd = open (argv[argc - 1], O_RDONLY);
    if (fd == -1)
      return -1;
    dup2 (fd, 0);
  }
  int ch;
  while (1)
  {
    ch = getchar ();
    if (ch == EOF)
      break;
    if (ch == '' || ch == ' ' || ch == '\t' || ch == '\n')
      continue;
    if (isdigit (ch))
    {
      cat_token (ch);
      while (1)
      {
        ch = getchar ();
        if ()
      }
    }
  }
   

  return 0;
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
