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
    if (is_empty(ccc))
      continue;
    putchar(ccc);
  }
  return 0;
}

