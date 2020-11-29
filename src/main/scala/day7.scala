import scala.io.Source

/*
--- Day 7: Recursive Circus ---

Wandering further through the circuits of the computer, you come upon a tower of programs that have gotten themselves
into a bit of trouble. A recursive algorithm has gotten out of hand, and now they're balanced precariously in a large tower.

One program at the bottom supports the entire tower. It's holding a large disc, and on the disc are balanced several
more sub-towers. At the bottom of these sub-towers, standing on the bottom disc, are other programs, each holding their
own disc, and so on. At the very tops of these sub-sub-sub-...-towers, many programs stand simply keeping the disc below
them balanced but with no disc of their own.

You offer to help, but first you need to understand the structure of these towers. You ask each program to yell out
their name, their weight, and (if they're holding a disc) the names of the programs immediately above them balancing
on that disc. You write this information down (your puzzle input). Unfortunately, in their panic, they don't do this in
an orderly fashion; by the time you're done, you're not sure which program gave which information.

Before you're ready to help them, you need to make sure your information is correct. What is the name of the bottom program?
 */

case class Program(name: String, weight: Int, holdingStrings: List[String], var holding: List[Program] = List(),
                   var parent: Option[Program] = None)
object Program {
  def apply(spec: String): Program = {
    val parts = spec.split(" ")
    val name = parts.head
    val weight = parts(1).substring(1, parts(1).length -1).toInt
    val holdingString = if (spec.contains("->")) parts.drop(3).mkString("").split(',').toList else List()
    new Program(name, weight, holdingString)
  }
}

object day7 extends App {

  val input: List[String] = Source.fromFile("inputs/day7.txt").mkString.split('\n').toList
  val programs: Map[String, Program] = input.map({p: String =>
    val prog = Program(p)
    prog.name -> prog
  }).toMap

  programs foreach { case (name, prog) =>
    prog.holdingStrings foreach { holdingString =>
      prog.holding = prog.holding :+ programs(holdingString)
      programs(holdingString).parent = Some(prog)
    }
  }

  println(programs.find(_._2.parent.isEmpty).get._1)
}

object day7_2 extends App {
  def calculateWeight(fromProgram: Program): Int = {

  }
}