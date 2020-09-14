package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c < 0) 0
    else if (c == 0) 1
    else if (c == r) 1
    else if (c > r) 0
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    val parens: List[Char] = List()
    def run(chars: List[Char], parens: List[Char]): Boolean = {
      if (chars.isEmpty && parens.isEmpty) true
      else if (chars.isEmpty && !parens.isEmpty) false
      else if (chars.head != '(' && chars.head != ')') run(chars.tail, parens)
      else if (chars.head == '(') run(chars.tail, chars.head +: parens)
      else {
        // chars.head == ')'
        if (parens.isEmpty) false
        else run(chars.tail, parens.tail)
      }
    }
    run(chars, parens)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) return 1
    if (money <= 0) return 0

    var count = 0
    for (i <- 0 to coins.length - 1) {
      count += countChange(money - coins(i), coins.slice(i, coins.length))
    }
    return count
  }
}
