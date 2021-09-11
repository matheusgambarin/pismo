echo 'Gradle assemble version'

./gradlew assemble

echo 'Execute docker build'

docker build -t pizmo-api:latest   -f ./DockerFile .

echo 'Start application'

docker run -p 8090:8090 pizmo-api
