FROM openjdk
WORKDIR /build/
COPY . /build/
RUN ./gradlew jar

FROM openjdk:11.0.3-jre-slim
WORKDIR /root/
COPY --from=0 /build/build/libs/slack-emoji-exporter-1.0-SNAPSHOT.jar .
CMD ["java", "-jar", "./slack-emoji-exporter-1.0-SNAPSHOT.jar"]