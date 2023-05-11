# Shared Expense API

Small API to manage groups of shared expenses between friends using Kotlin and Micronaut.

## Requirements

To build the application it is necessary to install the following dependencies:

- [Maven](https://maven.apache.org/)
- [JDK 17](https://openjdk.org/projects/jdk/17/)
- [Docker](https://www.docker.com/) (if you want to build it as a dockerized application)

## Run locally

To deploy the application locally just run:

```bash
mvn clean install
java -jar .\target\sharedexpense-0.1-SNAPSHOT.jar
```
These commands raise the application listening on port 8080.

If you want to build the application in a dockerized environment:
```bash
mvn clean install
mvn io.fabric8:fabric8-maven-plugin:build
docker run -p 8080:8080 \
  sharedexpense
```

### Demo mode

If you wish, you can easily run a demo of this API, including a local database with test data.

To do so, just run:

```bash
mvn clean install
mvn io.fabric8:fabric8-maven-plugin:build
docker-compose up
```
