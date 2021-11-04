FROM openjdk:8
WORKDIR /app/
COPY java ./
RUN javac -cp antlr-4.9.2-complete.jar:antlr:.: Main.java
