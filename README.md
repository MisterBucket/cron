# Mike's Cron Expression Parser

## Intro
This is a simple cron expression parser that supports minute, hour, day of month, month, day of week dimensions.  For more informaion please refer to the [CRON Expression](https://en.wikipedia.org/wiki/Cron#CRON_expression) article on Wikipedia.

## Getting Started
This solution is built using Java 11.

1. Check if you have Java 11 installed:  ```java -version```
2. If you are not on 11+, please follow the [Installation Guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html)

## Recommended IDE
[IntelliJ](https://www.jetbrains.com/idea/) is strongly recommended for working on this project. 

## Running the App
This app is executed using [Gradle](https://gradle.org/). All files are included, so no further installation is required.

### Run to get usage

```
./gradlew run

> Task :run
Usage:   ./gradlew run --args="[minute] [hour] [day of month] [month] [day of week] [command]"
Example: ./gradlew run --args="*/15 0 1,15 * 1-5 /usr/bin/find"
```

### Run with a cron expression and command

```
./gradlew run --args="*/15 0 1,15 * 1-5 /usr/bin/find"

> Task :run
minute         0 15 30 45
hour           0
day of month   1 15
month          1 2 3 4 5 6 7 8 9 10 11 12
day of week    1 2 3 4 5
command        /usr/bin/find
```

### Run unit tests from the command line

Unit tests can be run from the command line using Gradle:

```./gradlew test```

However, it is strongly recommended that you execute tests in [IntelliJ](https://www.jetbrains.com/idea/)
