Toy Robot Simulator
===================

There are three different implementations for this project. Please find the details for each of them below.

Java 
----
[![Build Status](https://travis-ci.org/Kalimaha/ToyRobotSimulator.svg?branch=java)](https://travis-ci.org/Kalimaha/ToyRobotSimulator)
[![Coverage Status](https://coveralls.io/repos/github/Kalimaha/ToyRobotSimulator/badge.svg?branch=java)](https://coveralls.io/github/Kalimaha/ToyRobotSimulator?branch=java)

This implementation can be found in the [java branch](https://github.com/Kalimaha/ToyRobotSimulator/tree/java) of the project. The project has been developed with [Maven](https://maven.apache.org/). To run the tests simply invoke:

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

JavaScript
----------

This implementation can be found in the [javascript branch](https://github.com/Kalimaha/ToyRobotSimulator/tree/javascript) of the project. Tests have been developed using the BDD framework [Jasmine](http://jasmine.github.io/). To run the test please open the ```test/SpecRunner.html``` web page with a modern browser. A simple [index.html](https://github.com/Kalimaha/ToyRobotSimulator/blob/javascript/index.html) test page developed with [Bootstrap](http://getbootstrap.com/) is available in the project root. This page offers a command line to input instruction (*separated by the ```;``` character*) to interact with the toy robot. The following example:

```
PLACE 0,0,NORTH; MOVE; REPORT;
```

produces the following output:

```
X: 0, Y: 1, FACING: NORTH
```

RequireJS
---------

This implmentation can be found in the [requirejs branch](https://github.com/Kalimaha/ToyRobotSimulator/tree/requirejs) of the project. RequireJS has been added to the JavaScript implementation of the project, which allows the asynchronous module loading and the integration of the Jasmine test with TravisCI. The continuous integration can be found at [this link](https://travis-ci.org/Kalimaha/ToyRobotSimulator/builds/113849016). The test [index.html](https://github.com/Kalimaha/ToyRobotSimulator/blob/requirejs/index.html) has been altered with a reference to the [main.js](https://github.com/Kalimaha/ToyRobotSimulator/blob/requirejs/src/js/main.js) which is in charge of loading the required modules and implement the business logic. The demo page offers a command line to input instruction (*separated by the ```;``` character*) to interact with the toy robot. The following example:

```
PLACE 0,0,NORTH; MOVE; REPORT;
```

produces the following output:

```
X: 0, Y: 1, FACING: NORTH
```
