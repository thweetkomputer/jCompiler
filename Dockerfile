FROM gcc:9
COPY ./l1/lexcial-analyser.c .
RUN gcc lexical-analyser.c -o lexical-analyser
RUN chmod +x lexical-analyser

