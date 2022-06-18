# Openjdk11 image
FROM openjdk:11-jdk

# Author
MAINTAINER Lucasf

# Fix Work directory
WORKDIR /app

# Set jar file path variable
ARG JAR_FILE=target/springdevforum-0.0.1-SNAPSHOT.jar
ARG PORT=8080

# Copy jar file and move to /app directory
COPY ${JAR_FILE} /app/app.jar

# Run on port 8080
EXPOSE 8080

# Run the jar
ENTRYPOINT [ "java", "-Xmx512m", "-Dserver.port${PORT}", "-jar","/app/app.jar" ]
# "-Xmx512m" - Limit server memory at 512mb
# "-Dserver.port${PORT}" Server.port - Heroku variable

# mvn -DPORT=8080 -DSPRING_PROFILE_ACTIVE=prod -DFORUM_DATABASE_URL=*** clean package
# java -jar target/forum.jar

# docker build -t spring/forum .
# docker run -p 8080:8080 -e PORT='8080' -e SPRING_PROFILE_ACTIVE='prod' -e FORUM_DATABASE_URL='***' spring/forum