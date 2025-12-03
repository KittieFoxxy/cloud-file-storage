FROM maven:3.9.9-eclipse-temurin-17-alpine@sha256:c1b318f88ab1bcf7aae3e0fceff4f5890fe6f7c910ead1c538bd5cada01df6c6 AS build
WORKDIR /build
COPY src src
COPY pom.xml pom.xml
RUN --mount=type=cache,target=/root/.m2  \
    mvn clean package &&  \
    java -Djarmode=layertools  \
    -jar target/*.jar  \
    extract --destination target/extracted

FROM eclipse-temurin:17.0.17_10-jre-noble@sha256:fe29e96933d584ff5f2f8efa148103517974941f72e68e6db13e1888e9919cfa
RUN groupadd --system javauser && useradd --system --gid javauser --shell /usr/sbin/nologin --no-create-home javauser
WORKDIR /opt/app
COPY --from=build /build/target/extracted/application .
COPY --from=build /build/target/extracted/dependencies .
COPY --from=build /build/target/extracted/snapshot-dependencies .
COPY --from=build /build/target/extracted/spring-boot-loader .
RUN chown -R javauser:javauser .
USER javauser
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
