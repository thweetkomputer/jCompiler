FROM openjdk:8
WORKDIR /
COPY java ./
RUN ls
RUN javac Main.java
