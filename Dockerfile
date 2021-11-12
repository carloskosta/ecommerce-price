FROM maven:3-jdk-11 as builder
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
RUN mvn -B dependency:resolve dependency:resolve-plugins -Dcheckstyle.skip -DskipTests
COPY src /build/src
RUN mvn package -Dcheckstyle.skip -DskipTests


FROM openjdk:11-slim as runtime
EXPOSE 8080
ENV APP_HOME /app
ENV JAVA_OPTS=""

RUN mkdir $APP_HOME
RUN mkdir $APP_HOME/config
RUN mkdir $APP_HOME/log

VOLUME $APP_HOME/log
VOLUME $APP_HOME/config

WORKDIR $APP_HOME
COPY --from=builder /build/target/*.jar ecommerce-price.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ecommerce-price.jar" ]