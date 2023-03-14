FROM khipu/openjdk17-alpine
COPY /src/main/resources/files /src/main/resources/files
ADD /target/Supplies-0.0.1-SNAPSHOT.jar Supplies.jar
ENTRYPOINT ["java", "-jar", "Supplies.jar"]