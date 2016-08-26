package com.rea

import org.scalatest.FunSuite

class TestToyRobot1 extends FunSuite {

  test("Print the position in the desired format.") {
    assert(Position(0, 0).toString == "(0, 0)")
  }

  test("It fixes the orientation") {
    assert(Orientation.abs(0) == 0)
    assert(Orientation.abs(360) == 0)
    assert(Orientation.abs(90) == 90)
    assert(Orientation.abs(450) == 90)
    assert(Orientation.abs(-90) == 270)
  }

  test("Show the orientation out of the numerical value.") {
    assert(Orientation.degrees2orientation(0) == "North")
    assert(Orientation.degrees2orientation(90) == "East")
    assert(Orientation.degrees2orientation(180) == "South")
    assert(Orientation.degrees2orientation(270) == "West")
  }

  test("Turn left.") {
    assert(Orientation.left(Orientation(0)).degrees == 270)
    assert(Orientation.left(Orientation(90)).degrees == 0)
    assert(Orientation.left(Orientation(180)).degrees == 90)
    assert(Orientation.left(Orientation(270)).degrees == 180)
  }

  test("Turn right.") {
    assert(Orientation.right(Orientation(0)).degrees == 90)
    assert(Orientation.right(Orientation(90)).degrees == 180)
    assert(Orientation.right(Orientation(180)).degrees == 270)
    assert(Orientation.right(Orientation(270)).degrees == 0)
  }

  test("Move North.") {
    assert(ToyRobot.move(ToyRobot(Position(0, 0), Orientation(0))).position.x == 0)
    assert(ToyRobot.move(ToyRobot(Position(0, 0), Orientation(0))).position.y == 1)
  }

  test("Move South.") {
    assert(ToyRobot.move(ToyRobot(Position(0, 0), Orientation(180))).position.x == 0)
    assert(ToyRobot.move(ToyRobot(Position(0, 0), Orientation(180))).position.y == -1)
  }

  test("Move East.") {
    assert(ToyRobot.move(ToyRobot(Position(0, 0), Orientation(90))).position.x == 1)
    assert(ToyRobot.move(ToyRobot(Position(0, 0), Orientation(90))).position.y == 0)
  }

  test("Move West.") {
    assert(ToyRobot.move(ToyRobot(Position(0, 0), Orientation(270))).position.x == -1)
    assert(ToyRobot.move(ToyRobot(Position(0, 0), Orientation(270))).position.y == 0)
  }

  test("Simulate.") {
    val actions = List(ToyRobot.left(_), ToyRobot.move(_), ToyRobot.right(_))
    val state = Simulator.simulate(actions)
    assert(state.position.x == -1)
    assert(state.position.y == 0)
    assert(state.orientation == "North")
  }

  test("Report.") {
    assert(ToyRobot(Position(0, 0), Orientation(0)).toString == "ToyRobot is located at (0, 0) and it is currently facing North.")
  }
}
