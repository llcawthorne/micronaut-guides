./gradlew assemble
docker build . -t graal
docker run --network host graal
