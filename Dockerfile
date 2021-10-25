FROM ubuntu:20.04
FROM gcc:9
COPY lab1.c lexical.c lexical.h dist ./
RUN gcc -g lab2.c lexical.c -o lab2
RUN chmod +x lab2

