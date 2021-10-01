FROM ubuntu:20.04
FROM gcc:9
RUN ls -la
RUN gcc l1/lexical-analyser.c -o lexical-analyser
RUN chmod +x lexical-analyser

