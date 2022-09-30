FROM eclipse-temurin
WORKDIR app
COPY target/*.jar employees.jar
ENTRYPOINT ["java", "-jar", "employees.jar"]