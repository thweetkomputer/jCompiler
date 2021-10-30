FROM ubuntu:20.04
FROM gcc:9
COPY lab3.c lexical.c lexical.h cal.py ./
RUN gcc -g lab3.c lexical.c -o lab3
RUN chmod +x lab3

