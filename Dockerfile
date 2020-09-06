#   ---------Dockerfile para aplicacao SpringBoot---------

FROM openjdk:14.0.2

ADD target/desafio-java-brasil-prev-1.0.0-SNAPSHOT.jar desafio-java-brasil-prev.jar


CMD ["java", "-jar", "desafio-java-brasil-prev.jar"]