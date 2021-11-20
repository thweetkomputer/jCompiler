#include <stdio.h>
#include <stdlib.h>


int main (int argc, char *argv[])
{
  int ccc;
  while ((ccc = getchar())!=EOF){
    if (ccc == ' ') {
      putchar(ccc);
      continue;
    }
    if (ccc == '\t' || ccc == '\r' || ccc == '\n')
      continue;
    putchar(ccc);
  }
  return 0;
}

