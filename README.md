[![Build Status](https://travis-ci.org/Kalimaha/ToyRobotSimulator.svg?branch=java-2)](https://travis-ci.org/Kalimaha/ToyRobotSimulator)
[![Coverage Status](https://coveralls.io/repos/github/Kalimaha/ToyRobotSimulator/badge.svg?branch=java)](https://coveralls.io/github/Kalimaha/ToyRobotSimulator?branch=java)

Toy Robot Simulator
===================

Build
-----
The project has been developed using [Maven](https://maven.apache.org/). To build it, please execute the command ```mvn``` from the root of the project. The output JAR file will be placed in the ```target``` folder.

Tests
-----
To run the [JUnit](http://junit.org/)-based tests, please execute the command ```mvn test``` from the root of the project. Part ot the tests are file-based. The text files containing the lists of commands are available in the ```src/main/resources``` folder.

Continuous Integration
---------------------
The project is available on the [TravisCI](https://travis-ci.org/) platform for continuous integration at [this link](https://travis-ci.org/Kalimaha/ToyRobotSimulator/builds/114914770);

Usage
-----
The simulator is available in the dist folder as JAR file. The simulator accepts instructions from the command line. The following example:
```
java -jar dist/toy-robot-simulator.jar "place 0,0,north" "move" "report"
```
produces the following output:
```
X: 0, Y: 1, FACING: NORTH
```