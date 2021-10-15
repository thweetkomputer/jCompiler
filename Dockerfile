FROM ubuntu:20.04
FROM gcc:9
COPY lab1.c lexical.c .
RUN gcc -g lab1.c lexical.c -o lab1
RUN chmod +x lab1

