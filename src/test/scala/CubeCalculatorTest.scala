import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.mutable

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Feb 2025, 9:23 AM
 */
class CubeCalculatorTest extends AnyFlatSpec {
  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new mutable.Stack[Int]
    stack.push(1)
    stack.push(2)
    assert(stack.pop() === 2)
    assert(stack.pop() === 1)
  }
}
