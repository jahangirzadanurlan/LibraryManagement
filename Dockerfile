FROM adoptopenjdk/openjdk11
EXPOSE 8080
ARG JAR_FILE=build/libs/library-managment-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} library.jar
ENTRYPOINT ["java", "-jar","/library.jar"]