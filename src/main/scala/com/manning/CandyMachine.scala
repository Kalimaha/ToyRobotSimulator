package com.manning

/* These are the possible actions that I can apply on a Machine. */
sealed trait Input
  case object Coin extends Input
  case object Turn extends Input

/* This is a Machine, which can be locked and contains candies and coins. */
case class Machine(locked: Boolean, candies: Int, coins: Int)

object Machine {

  /* This is the business logic that creates a new Machine based on the previous state and the current action. */
  def update(i: Input)(m: Machine): ((Int, Int), Machine) = (i, m) match {
    case (Turn, Machine(true, _, _))  => ((m.candies, m.coins), m)
    case (Coin, Machine(false, _, _)) => ((m.candies, m.coins), m)
    case (Turn, Machine(false, 0, _)) => ((m.candies, m.coins), m)
    case (Coin, Machine(true, _, _))  =>
      ((m.candies, m.coins + 1), Machine(locked = false, candies = m.candies, coins = m.coins + 1))
    case (Turn, Machine(false, _, _)) =>
      ((m.candies - 1, m.coins), Machine(locked = true, candies = m.candies - 1, coins = m.coins))
  }
}

case class State[S, A](run: S => (A, S)) {

  /* S == Machine. */
  def flatMap[B](f: A => State[S, B]): State[S, B] = {
    State(m => {
      val (newValue, newState) = run(m)
      f(newValue).run(newState)
    })
  }

  def map[B](f: A => B): State[S, B] = State {
    (s: S) => {
      val (newValue, newState) = run(s)
      (f(newValue), newState)
    }
  }
}

object CandyMachine {

  /* http://boldradius.com/blog-post/U6A9eCwAACwAFHXh/functional-state-in-scala */
  def main(args: Array[String]) {

    stupidWay()
    println("--------------------------------------------------")
    stupidWayFor()
    println("--------------------------------------------------")
    almostThere()
  }

  def almostThere(): Unit = {

    def update(input: Input) = State[Machine, (Int, Int)](s => Machine.update(input)(s))

    /* How to do this with for comprehension? */
    /* How to do this iterating over a List[Input]? */
    val out = update(Coin).flatMap(
      firstResult =>
        update(Turn).map(
          secondResult => "Candies: " + secondResult._1 + ", Coins: " + secondResult._2
        )
    )

    /* Compute the output by providing the initial state. */
    val (resultString, _) = out.run(Machine(locked = true, 10, 0))
    println(resultString)
  }

  /* This is still stupid, with a or loop. */
  def stupidWayFor(): Unit = {

    val inputs = List(Coin, Turn)
    var newState = Machine(locked = true, 10, 0)

    for (i <- inputs) {
      println(i)
      println(i + " => " + newState)
      val out = Machine.update(i)(newState)
      newState = out._2
      println("\tnew state:\t" + newState)
      println("\tcandies:\t" + out._1._1)
      println("\tcoins:\t\t" + out._1._2)
      println()
    }
  }

  /* This is the very stupid way to implement it. */
  def stupidWay(): Unit = {

    /* These are the action that I wan to apply on an initial Machine. */
    val input1 = Coin
    val input2 = Turn
    val initialState = Machine(locked = true, 10, 0)

    /* First action... */
    val ((candies1, coins1), newState1) = Machine.update(input1)(initialState)
    println(input1 + " => " + initialState)
    println("\tnew state:\t" + newState1)
    println("\tcandies:\t" + candies1)
    println("\tcoins:\t\t" + coins1)
    println()

    /* Second action... */
    val ((candies2, coins2), newState2) = Machine.update(input2)(newState1)
    println(input2 + " => " + newState1)
    println("\tnew state:\t" + newState2)
    println("\tcandies:\t" + candies2)
    println("\tcoins:\t\t" + coins2)
    println()
  }
}