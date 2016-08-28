package com.manning

/* These are the possible actions that I can apply on a Machine. */
sealed trait Input
  case object Coin extends Input
  case object Turn extends Input

/* This is a Machine, which can be locked and contains candies and coins. */
case class Machine(locked: Boolean, candies: Int, coins: Int)

object Machine {

  /* This is the business logic that creates a new Machine based on the previous state and the current action. */
  def update(i: Input)(m: Machine): (Machine, (Int, Int)) = (i, m) match {
    case (Turn, Machine(true, _, _))  => (m, (m.candies, m.coins))
    case (Coin, Machine(false, _, _)) => (m, (m.candies, m.coins))
    case (Turn, Machine(false, 0, _)) => (m, (m.candies, m.coins))
    case (Coin, Machine(true, _, _))  =>
      (Machine(locked = false, candies = m.candies, coins = m.coins + 1), (m.candies, m.coins + 1))
    case (Turn, Machine(false, _, _)) =>
      (Machine(locked = true, candies = m.candies - 1, coins = m.coins), (m.candies - 1, m.coins))
  }
}

object CandyMachine {

  def main(args: Array[String]) {

    stupidWay()
    println("--------------------------------------------------")
    stupidWayFor()
  }

  def stupidWayFor(): Unit = {

    val inputs = List(Coin, Turn)
    var newState = Machine(locked = true, 10, 0)

    for (i <- inputs) {
      println(i)
      println(i + " => " + newState)
      val out = Machine.update(i)(newState)
      newState = out._1
      println("\tnew state:\t" + newState)
      println("\tcandies:\t" + out._2._1)
      println("\tcoins:\t\t" + out._2._2)
      println()
    }
  }

  def stupidWay(): Unit = {

    /* These are the action that I wan to apply on an initial Machine. */
    val input1 = Coin
    val input2 = Turn
    val initialState = Machine(locked = true, 10, 0)

    /* First action... */
    val (newState1, (candies1, coins1)) = Machine.update(input1)(initialState)
    println(input1 + " => " + initialState)
    println("\tnew state:\t" + newState1)
    println("\tcandies:\t" + candies1)
    println("\tcoins:\t\t" + coins1)
    println()

    /* Second action... */
    val (newState2, (candies2, coins2)) = Machine.update(input2)(newState1)
    println(input2 + " => " + newState1)
    println("\tnew state:\t" + newState2)
    println("\tcandies:\t" + candies2)
    println("\tcoins:\t\t" + coins2)
    println()
  }
}