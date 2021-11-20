FROM gcc:10.2
COPY lab2.c ./
RUN gcc -g lab2.c -o lab2
RUN chmod +x lab2

