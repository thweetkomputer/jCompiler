FROM java:8-jdk
WORKDIR /app/
COPY java ./
RUN ls
RUN ls antlr
RUN javac -cp antlr-4.9.2-complete.jar:antlr: Main.java
