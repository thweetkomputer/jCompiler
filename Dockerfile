FROM openjdk:8
WORKDIR /WORK/
COPY java ./
RUN ls
WORKDIR /WORK/antlr/
RUN ls
WORKDIR /WORK/
RUN javac Main.java
