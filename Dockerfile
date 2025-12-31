FROM eclipse-temurin:21-jdk AS build

WORKDIR /build

# Copiamos Gradle wrapper y config primero (cache)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew

COPY src src

RUN ./gradlew clean build -x test

FROM eclipse-temurin:21-jre

RUN apt update && apt install -y wget ffmpeg python3 \
    && wget https://github.com/yt-dlp/yt-dlp/releases/latest/download/yt-dlp -O /usr/local/bin/yt-dlp \
    && chmod +x /usr/local/bin/yt-dlp

WORKDIR /app
COPY --from=build /build/build/libs/*.jar app.jar

ENV DOWNLOADS_DIRECTORY=/downloads/
VOLUME ["/downloads"]

CMD ["java", "-jar", "app.jar"]
