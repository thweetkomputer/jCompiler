FROM ubuntu:16.04
FROM gcc:9
WORKDIR /l1/
COPY ./lexcial-analyser.c ./
RUN gcc lexical-analyser.c -o lexical-analyser
RUN chmod +x lexical-analyser

