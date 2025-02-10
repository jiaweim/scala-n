/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 07 Feb 2025, 10:38 AM
 */
object LastIndexWhereDemo {
  def main(args: Array[String]): Unit = {
    val ls = List(1, 2, 3, 4, 5, 6)
    println(ls.lastIndexWhere(ele => ele < 4))
    println(ls.lastIndexWhere(ele => ele < 4, 0))
    println(ls.lastIndexWhere(ele => ele < 4, 1))
    println(ls.lastIndexWhere(ele => ele < 4, 2))

  }
}
