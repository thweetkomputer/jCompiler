FROM ubuntu:20.04
FROM gcc:9
COPY l1/lexical-analyser.c ./
RUN gcc lexical-analyser.c -o lexical-analyser
RUN chmod +x lexical-analyser

