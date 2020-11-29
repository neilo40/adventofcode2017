import scala.collection.mutable
import scala.io.Source

class Instruction(_text: String) {

  private val parts = _text.split(" ").toList
  val registerToIncOrDec: String = parts.head
  val incOrDec: String = parts(1)
  val amountToIncOrDec: Int = parts(2).toInt
  val registerToCheck: String = parts(4)
  val condition: String = parts(5)
  val valueToCheck: Int = parts(6).toInt

  def calculate(registers: mutable.Map[String, Int]): Unit = {
    if (!registers.isDefinedAt(registerToIncOrDec)) registers put (registerToIncOrDec, 0)
    if (!registers.isDefinedAt(registerToCheck)) registers put (registerToCheck, 0)

    val doThing = condition match {
      case ">=" => registers(this.registerToCheck) >= this.valueToCheck
      case ">" => registers(this.registerToCheck) > this.valueToCheck
      case "<=" => registers(this.registerToCheck) <= this.valueToCheck
      case "<" => registers(this.registerToCheck) < this.valueToCheck
      case "==" => registers(this.registerToCheck) == this.valueToCheck
      case "!=" => registers(this.registerToCheck) != this.valueToCheck
    }

    if (doThing){
      incOrDec match {
        case "inc" => registers(this.registerToIncOrDec) += this.amountToIncOrDec
        case "dec" => registers(this.registerToIncOrDec) -= this.amountToIncOrDec
      }
    }
  }

}

object day8 extends App {

  var registers = mutable.Map[String, Int]()
  val instructions = Source.fromFile("inputs/day8.txt").mkString.split('\n').toList map { new Instruction(_) }
  var maxSeen = 0

  instructions foreach { i =>
    i.calculate(registers)
    val maxNow = registers.values.max
    maxSeen = if (maxNow > maxSeen) maxNow else maxSeen
  }

  println("The largest register value is: " + registers.values.max)
  println("Katie, the largest value I ever did see was: " + maxSeen)

}
