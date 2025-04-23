# Базовый образ с JDK 17
FROM eclipse-temurin:17-jdk-jammy

# Рабочая директория
WORKDIR /app

# Копируем собранный JAR
COPY build/libs/*.jar app.jar

# Порт приложения
EXPOSE 8080

# Команда запуска
ENTRYPOINT ["java", "-jar", "app.jar"]