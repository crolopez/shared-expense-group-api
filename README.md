# Shared Expense API

Small API to manage groups of shared expenses between friends using Kotlin and Micronaut.

## Requirements

To build the application it is necessary to install the following dependencies:

- [Maven](https://maven.apache.org/)
- [JDK 17](https://openjdk.org/projects/jdk/17/)
- [Docker](https://www.docker.com/) (if you want to build it as a dockerized application)

## Configuration

In order to launch the application, it is necessary to configure the following environment variables:

| Variable         | Description                                                                             |
|------------------|-----------------------------------------------------------------------------------------|
| DB_URL           | Datasource of a PostgreSQL database. Example: jdbc:postgresql://localhost:5432/postgres |
| DB_USER          | Database username                                                                       |
| DB_PASSWORD      | Database password                                                                       |
| JWT_SECRET       | Secret for the authentication token generation                                          |

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
  --env DB_URL="<DB_URL>" \
  --env DB_USER="<DB_USER>" \
  --env DB_PASSWORD="<DB_PASSWORD>" \
  --env JWT_SECRET="<JWT_SECRET>" \
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

After that, you can just import [this Insomnia collection](./insomnia-collection.json) to test the API.

You can see an explanation of the demo scenario data [here](./.github/docs/DEMO.md).