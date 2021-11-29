#include <stdio.h>
/* Input & output functions */
int getint() {
    int t;
    scanf("%d", &t);
    return t;
}
int getch() {
    char c;
    scanf("%c", &c);
    return (int)c;
}
int getarray(int a[]) {
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; i++)
        scanf("%d", &a[i]);
    return n;
}
void putint(int a) { printf("%d", a); }
void putch(int a) { printf("%c", a); }
void putarray(int n, int a[]) {
    printf("%d:", n);
    for (int i = 0; i < n; i++)
        printf(" %d", a[i]);
    printf("\n");
}
int ans[50], sum = 0, n; 
  
 int row[50], line1[50], line2[100]; 
  
 void printans() { 
     sum   = sum + 1; 
     int i = 1; 
     while (i <= n) { 
         putint(ans[i]); 
         if (i == n) { 
             putch(10); 
             return; 
         } else 
             putch(32); 
         i = i + 1; 
     } 
 } 
  
 void f(int step) { 
     int i = 1; 
     while (i <= n) { 
         if (row[i] != 1 && line1[step + i] == 0 && !line2[n + step - i]) { 
             ans[step] = i; 
             if (step == n) 
                 printans(); 
             row[i]              = 1; 
             line1[step + i]     = 1; 
             line2[n + step - i] = 1; 
             f(step + 1); 
             row[i]              = 0; 
             line1[step + i]     = 0; 
             line2[n + step - i] = 0; 
         } 
         i = i + 1; 
     } 
 } 
  
 int main() { 
     int N = getint(); 
     while (N > 0) { 
         n = getint(); 
         f(1); 
         N = N - 1; 
     } 
     return 0; 
 }

