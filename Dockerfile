FROM adoptopenjdk/openjdk11:jdk-11.0.6_10-slim
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} ringier.jar
ADD ringier.db ringier.db
ADD build/libs/ringier-0.0.1-SNAPSHOT.jar ringier.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","ringier.jar"]
