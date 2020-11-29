import scala.io.Source

/*
A new system policy has been put in place that requires all accounts to use a passphrase instead of simply a password.
A passphrase consists of a series of words (lowercase letters) separated by spaces.

To ensure security, a valid passphrase must contain no duplicate words.

For example:

aa bb cc dd ee is valid.
aa bb cc dd aa is not valid - the word aa appears more than once.
aa bb cc dd aaa is valid - aa and aaa count as different words.
The system's full passphrase list is available as your puzzle input. How many passphrases are valid?
 */

object day4 extends App {

  def genWordsLists: List[List[String]] = {
    val input = Source.fromFile("inputs/day4.txt").mkString
    val passphraseTexts = input.split("\n").toList
    passphraseTexts map { text =>
      text.split(" ").toList
    }
  }

  def validPassphrases(passphraseWords: List[List[String]]) = passphraseWords filter { words =>
    val lengthOfList = words.length
    val sizeOfSet = words.toSet.size
    lengthOfList == sizeOfSet
  }

  println(validPassphrases(genWordsLists).length)
}

object day4_2 extends App {

  def genWordsLists: List[List[String]] = {
    val input = Source.fromFile("inputs/day4.txt").mkString
    val passphraseTexts = input.split("\n").toList
    passphraseTexts map { text =>
      val words = text.split(" ").toList
      words map { word => word.sorted }
    }
  }

  println(day4.validPassphrases(genWordsLists).length)
}
