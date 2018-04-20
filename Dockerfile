FROM java:8-jre-alpine
ADD  ./target/backend-1.0-SNAPSHOT.jar /app/backend.jar

ENTRYPOINT "java -jar /app/backend.jar"

expose 9000
