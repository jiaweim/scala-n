/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 07 Feb 2025, 7:21 PM
 */
object MapDemo {

  def main(args: Array[String]): Unit = {
    val a = List(3.0, 2.0, 10.0, 6.0, 9.0, 5.0, 4.0)
    val ls = List(1, 2, 4, 6)
    val s = ls.map(a)
    println(s)
  }
}
