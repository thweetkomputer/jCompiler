FROM openjdk:8
WORKDIR /app/
COPY java ./
RUN ls
RUN ls antlr
RUN javac Main.java
