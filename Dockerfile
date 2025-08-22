# -------- Build stage --------
FROM gradle:8.9-jdk21-alpine AS builder

WORKDIR /app

# Copy Gradle wrapper + build files
COPY gradlew build.gradle.kts settings.gradle.kts ./
COPY gradle/ gradle/

# Pre-download dependencies (cached unless build.gradle changes)
RUN ./gradlew dependencies --no-daemon || return 0

# Copy source code
COPY src/ src/

# Build fat jar (skip tests for faster builds)
RUN ./gradlew clean bootJar -x test --no-daemon

# -------- Runtime stage --------
FROM eclipse-temurin:21-jre AS runner

WORKDIR /app

# Copy built jar from builder
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# JVM options (can be overridden with -e JAVA_OPTS)
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Distroless has no shell → must use array form
ENTRYPOINT ["java", "-jar", "app.jar"]
