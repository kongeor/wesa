FROM openjdk:8-alpine

COPY target/uberjar/wesa.jar /wesa/app.jar

CMD ["java", "-jar", "/wesa/app.jar"]
