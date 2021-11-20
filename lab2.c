#include <stdio.h>
#include <stdlib.h>


int main (int argc, char *argv[])
{
  int ccc;
  while ((ccc = getchar())!=EOF){
    if (ccc == ' ' || ccc == '\t') {
      putchar(ccc);
      continue;
    }
    if (ccc == '\r' || ccc == '\n') {
      printf (" $\\n$ ");
      continue;
    }
    putchar(ccc);
  }
  return 0;
}

