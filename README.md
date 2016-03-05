Toy Robot Simulator
===================

There are three different implementations for this project. Please find the details for each of them below.

Java 
----
[![Build Status](https://travis-ci.org/Kalimaha/ToyRobotSimulator.svg?branch=java)](https://travis-ci.org/Kalimaha/ToyRobotSimulator)
[![Coverage Status](https://coveralls.io/repos/github/Kalimaha/ToyRobotSimulator/badge.svg?branch=java)](https://coveralls.io/github/Kalimaha/ToyRobotSimulator?branch=java)

This implementation can be found in the [Java Branch](https://github.com/Kalimaha/ToyRobotSimulator/tree/java) of this project. The project has been developed with [Maven](https://maven.apache.org/). To run the tests simply invoke:

```
mvn test
```

from the project's root. Tests can also be found in [Travis CI](https://travis-ci.org/) for continous integration at [this link](https://travis-ci.org/Kalimaha/ToyRobotSimulator/builds/113826176). Coverage of tests is available via [Coveralls](https://coveralls.io/) at [this link](https://coveralls.io/builds/5314297). For testing purposes, the simulator accepts either direct commands or files. Test files can be found in the ```src/main/resources/``` folder.

The simulator is available in the ```dist``` folder as JAR file. The simulator accepts instructions from the command line. The following example: 

```
java -jar dist/toy-robot-simulator.jar "place 0,0,north" "move" "report"
```

produces the following output:

```
X: 0, Y: 1, FACING: NORTH
```
