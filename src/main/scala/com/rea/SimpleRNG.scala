package com.rea

trait RNG {

  def nextInt: (Int, RNG)
}

case class SimpleRNG(seed: Long) extends RNG {

  def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }
}

object SimpleRNG {

  type State[S, +A] = S => (A, S)
  type Rand[+A] = RNG => (A, RNG)
//  type Rand[A] = State[RNG, A]

  def unit[A](a: A): Rand[A] = rng => (a, rng)

  /**
    * Considerando ad esempio A = Int e B = String, questa funzione accetta in input una funzione che
    * genera un a coppia (Int, RNG[Int]) ed una funziona che converte da Int a String, e restituisce
    * ancora una funzione che prende in inut un RNG[Int] e genera una coppia (String, RNG[String]).
    *
    * Internamente usa quindi il generatore di numeri casuali per generare delle stringhe casuali convertendo
    * gli interi in stringhe secondo una certa logica.
    *
    * Nel map 'classico' la funzione viene applicata agli elementi di un vettore, mentre in questa
    * versione di map la funzione di trasformazione viene applicata ad una funzione.
    */
  def map[A, B](s: Rand[A])(f: A => B): Rand[B] = {
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }
  }

  def flatMap[A,B](f: Rand[A])(g: A => Rand[B]): Rand[B] = {
    rng => {
      val (a, r1) = f(rng)
      g(a)(r1)
    }
  }
}

object Pippo {

//  case class State[S, +A](run: S => (A, S))
//
//  def modify[S](f: S => S): State[S, Unit] = for {
//    s <- get
//    _ <- set(f(s))
//  } yield ()
//
//  def get[S]: State[S, S] = State(s => (s, s))
//
//  def set[S](s: S): State[S, Unit] = State(_ => ((), s))

  case class State[Int, String](run: Int => (String, Int))

//  def modify(f: Int => Int): State[Int, Unit] = for {
//    s <- get
//    _ <- set(f(s))
//  } yield ()

  def get: State[Int, Int] = State(s => (s, s))

  def set(i: Int): State[Int, Unit] = State(_ => ((), i))
}

/**
  * inputs.map((i: Input) => ((f: Machine => Machine) => modify[Machine](f)).compose(update)(i))
  * :O
  */
