#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>

int main (int argc, char *argv[])
{
  printf ("%d\n", argc);
  // if (!strcmp (argv[argc - 2], "./lexical-analyser"))
  if (argc > 1)
  {
    int fd = open (argv[argc - 1], O_RDONLY);
    if (fd == -1)
      return -1;
    dup2 (fd, 0);
    char ch = getchar ();
    putchar (ch);
  } else {
    char ch = getchar ();
    putchar (ch);
  }
  

  return 0;
}
