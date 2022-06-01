#include <stdio.h>
#include <string.h>
#include <stdlib.h>
void _getchar ();

char ch;
int main (int argc, char *argv[])
{

  while (1) {
    _getchar();
    if (ch == EOF) {
      return 0;
    }
    putchar (ch);
  }
  return 0;
}


void _getchar ()
{
  ch = getchar ();
  if (ch == '/') {
    int next_ch = getchar ();
    if (next_ch == '/') {
      while (1) {
        next_ch = getchar ();
        if (next_ch == EOF) {
          ch = EOF;
          return;
        }
        if (next_ch == '\n') {
          ch = ' ';
          return;
        }
      }
    } else if (next_ch == '*') {
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
            ch = ' ';
            return;
          }
          ungetc (nnext_ch, stdin);
        }
      }
    } else {
      ungetc (next_ch, stdin);
    }
  }
}
