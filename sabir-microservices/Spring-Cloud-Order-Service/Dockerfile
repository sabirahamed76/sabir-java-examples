FROM openjdk:8

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} Spring-Cloud-Order-Service.jar

ENTRYPOINT ["java", "-jar", "/Spring-Cloud-Order-Service.jar" ]

EXPOSE 8088
