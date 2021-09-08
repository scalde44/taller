FROM adoptopenjdk/openjdk8
ADD microservicio/build/libs/taller-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]