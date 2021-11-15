FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY "./build/libs/sds-identity-provider-1.0.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
ENV SPRING_PROFILES_ACTIVE docker