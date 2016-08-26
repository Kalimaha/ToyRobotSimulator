package com.rea

import com.rea.Pippo.State
import org.scalatest.FunSuite

class TestSimpleRNG extends FunSuite {

  test("Map.") {
    def pippo(rng: RNG): (Int, RNG) = {
      val newSeed = (42 * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
      val nextRNG = SimpleRNG(newSeed)
      val n = (newSeed >>> 16).toInt
      (n, nextRNG)
    }
    val int2randString = SimpleRNG.map(pippo)(x => s"Lucky Number $x")
    val (randString1, nextGen1: SimpleRNG) = int2randString(SimpleRNG(42))
    println(randString1)
    println(nextGen1)
    println(nextGen1.seed)
    val (randString2, nextGen2) = int2randString(nextGen1)
    println(randString2)
    println(nextGen2)
    println(nextGen1.nextInt)
  }

  test("State.") {
    val s = State((x: Int) => ("Plus one.", x + 1))
    Pippo.set(42)
    val current = Pippo.get
    println(current)
    println(current.run)
  }

}