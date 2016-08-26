package com.rea

import scala.annotation.tailrec

case class Position(x: Int, y: Int) {

  override def toString = s"($x, $y)"
}

case class Orientation(degrees: Int) {

  override def toString = Orientation.degrees2orientation(degrees)
}

object Orientation {
  def abs(current: Int): Int = {
    if (current == 360) return 0
    else if (current < 0) return current + 360
    else if (current > 360) return current - 360
    current
  }

  def degrees2orientation(degrees: Int): String = degrees match {
    case 0    =>  "North"
    case 90   =>  "East"
    case 180  =>  "South"
    case 270  =>  "West"
  }

  def left(current: Orientation): Orientation = Orientation(abs(current.degrees - 90))
  def right(current: Orientation): Orientation = Orientation(abs(current.degrees + 90))
}

case class ToyRobot(position: Position, robotOrientation: Orientation) {

  def orientation: String = Orientation.degrees2orientation(robotOrientation.degrees)

  override def toString = s"ToyRobot is located at $position and it is currently facing $robotOrientation."
}

object ToyRobot {
  def move(current: ToyRobot): ToyRobot = current.robotOrientation.degrees match {
    case 0    =>  ToyRobot(Position(current.position.x, current.position.y + 1), current.robotOrientation)
    case 90   =>  ToyRobot(Position(current.position.x + 1, current.position.y), current.robotOrientation)
    case 180  =>  ToyRobot(Position(current.position.x, current.position.y - 1), current.robotOrientation)
    case 270  =>  ToyRobot(Position(current.position.x - 1, current.position.y), current.robotOrientation)
  }

  def left(current: ToyRobot): ToyRobot = ToyRobot(current.position, Orientation.left(current.robotOrientation))
  def right(current: ToyRobot): ToyRobot = ToyRobot(current.position, Orientation.right(current.robotOrientation))
}

trait Action
  case object Move extends Action
  case object Left extends Action
  case object Right extends Action
  case object Report extends Action

object Simulator {
  def simulate(actions: List[ToyRobot => ToyRobot]): ToyRobot = {
    @tailrec
    def loop(actions: List[ToyRobot => ToyRobot], toyRobot: ToyRobot): ToyRobot = actions match {
      case Nil  =>  toyRobot
      case h::t =>  loop(t, h(toyRobot))
    }
    loop(actions, ToyRobot(Position(0, 0), Orientation(0)))
  }
}

object ToyRobot1 {
  def main(args: Array[String]) { }
}