FROM gcc:9
COPY test.c .
RUN gcc test.c -o test
RUN chmod +x test
