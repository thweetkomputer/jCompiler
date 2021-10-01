FROM ubuntu:20.04
FROM gcc:9
WORKDIR /l1/
RUN gcc lexical-analyser.c -o lexical-analyser
RUN chmod +x lexical-analyser

