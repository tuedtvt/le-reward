# Introduction
Reward microservice of Loyalty Engine
* Manage rewards
* Integrate with reward providers

## Prerequisites
1. Java 11. With JAVA_HOME in environment variable.
2. Docker
3. IntelliJ IDEA. Note: community version is enough.
4. One of below MongoDB tools:
    1. Mongo Compass
    2. NoSQL Booster
    3. Robo3T
5. Google StyleGuide for Java: https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml

## IDEA Set up
## Building and Running application
### Building application
```bash
  ./gradlew build
```

### Running Docker Compose
```bash
  cd docker 
  docker-compose up -d
```
*What are defined in docker-compose.yml?*
1. MongoDB
2. Kafka
3. Redis

**Note:**
Change volumes configuration on docker-compose.yml to your specific location.


### Running Open API Generator
```bash
  ./gradlew openApiGenerate
```
**Note:**
To turn on generate code when compiling project, uncomment below line in build.gradle
```
  compileJava.dependsOn tasks.openApiGenerate
```
