# 面向 Java 程序员的 Scala 教程

## 简介

Scala 的优点：

- 没有分号：Scala 简化了 Java 大部分的模板，共享相同的底层类型和 runtime
- 无缝互操：Scala 可以使用任何现有的 Scala 库；包括 Java 标准库。而且几乎所有 Java 程序都可以在 Scala 中以相同的方式运行，只需转换调用语法
- 可扩展语言：Scala 这个名字来自 Scalable Language。Scala 不仅可以根据硬件资源和负载要求进行扩展，还可以根据程序员的技能水平进行扩展。与 Java 相比，这些功能可以提高开发人员的工作效率和代码的可读性

## 第一个 Scala 程序

下面编写标准的 Hello World 策划给你续：

```scala
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
  }
}
```

该程序的结构与 Java 类似：

- 入口是名为 `main` 的方法，其参数为字符串数组；
- 方法主体为对预定义方法 `println` 的调用
- `main` 方法没有返回值，因此返回类型声明为 `Unit`（等价于 java 的 `void`）

`object` 声明是 java 中没有的，表示 singleton 对象，即只有单个实例的类。因此，上面声明了一个名为 `HelloWorld` 的类，同时声明了该类的一个实例，也是 `HelloWorld`，该实例在第一次使用时按需创建。

与 java 另一个不同之处在于，此处的 `main` 方法未声明为 `static`。因为 scala 中 不存在 `static` 成员（方法或字段）。scala **不定义静态成员**，而是在 singleton 对象中声明这些成员。

**运行 Hello World**

将以上代码保存在 `HelloWorld.scala` 文件中，然后使用如下命令运行：

```sh
> scala HelloWorld.scala
```

该程序将被自动编译并执行。

## 使用 Java 库

Scala 的优势之一是它与 Java 代码的交互非常容易。`java.lang` 包中的所有类默认导入，其他类则需要显式导入。

下面通过一个示例进行演示。我们希望根据国家/地区格式化当前日期。Java 类库已经定义功能强大的类，如 `LocalDate` 和 `DateTimeFormatter`。由于 Scala 能与 Java 无缝互操，因此无需再 Scala 类库中实现等效类；相反，可以导入相应 Java 包中的类：

```scala
import java.time.LocalDate
import java.time.format.{DateTimeFormatter, FormatStyle}
import java.util.Locale

object FrenchDate {
  def main(args: Array[String]): Unit = {
    val now = LocalDate.now();
    val df = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
      .withLocale(Locale.FRANCE)
    println(df.format(now))
  }
}
```

scala 的 `import` 语句与 Java 的非常相似，但功能更强大。可以通过将多个类放在花括号中一起导入。另外，在 Scala 2 中，使用下划线 `_` 而不是星号 `*` 导入包中的所有类。

在 `main` 方法中，首先创建 Java 的 `DateTime` 类的一个实例，其中包含今天的日期。然后使用 `DateTimeFormatter.ofLocalizedDate` 定义日期格式，传入 `LONG` 格式样式，并导入 `FRANCE` 语言环境。最后，根据本地化的 `DateTimeFormatter` 实例打印格式化的当前日期。

另外，在 Scala 中可以直接继承 Java 类并实现 Java 接口。

### 第三方库

标准库通常不够用，与 Java 一样，Scala 的生态系统也建立在 Maven 之上。

大多数 Scala 项目都是用 sbt 构建，添加的第三方库由构建工具管理。Java 程序员，可能熟悉 Maven、Gradle 等工具，仍然可以使用这些工具来构建 Scala 项目，但使用 sbt 更常见。

## 一切都是对象

Scala 是一个纯粹的面向对象语言，一切都是对象，包括数字和函数。这方面它与 Java 不同，Java 将原始类型与引用类型进行了区分。

### 数字是对象

由于数字是对象，它们也有方面。事实上，像下面这样的算术表达式：

```scala
1 + 2 * 3 / x
```

完全由方面调用组成，它等同于以下表达式：

```scala
1.+(2.*(3)./(x))
```

这也意味着 `+`, `*` 等在 scala 中也是字段、方法的有效标识符。

### 函数是对象

在 Scala 中函数也是对象，超越了 Java 中对 lambda 表达式的支持。

与 Java 相比，函数对象和方法之间几乎没有区别：可以将方法作为参数传递，将其存储在变量中，并从其它函数返回函数，所有这些都不需要特殊语法。这种将函数作为值进行操作的能力是函数式编程的基础。

为了演示，考虑每秒执行一次操作的 timer 函数。要执行的操作由调用者作为函数值提供。

在下例中，timer 函数名为 `oncePerSecond`，它接受一个 call-back 函数作为参数。此函数的类型为 `() => Unit`，所有不带参数且不返回值的函数都是该类诶行。

在 `main` 中直接将 `timeFlies` 方法作为参数调用 `oncePerSecond`。最后这个程序会每秒打印一次 "time flies like an arrow..."，无限循环。

```scala
object Timer {
  def oncePerSecond(callback: () => Unit): Unit = {
    while (true) { callback(); Thread.sleep(1000) }
  }
  def timeFlies(): Unit = {
    println("time flies like an arrow...")
  }
  def main(args: Array[String]): Unit = {
    oncePerSecond(timeFlies)
  }
}
```

#### 匿名函数

在 Scala 中，lambda 表达式被称为匿名函数。当函数太短以至于不需要命名时很有用。

下面是 timer 的 lambda 版本，即将匿名函数传递给 `oncePerSecond`，而不是 `timeFlies`：

```scala
object TimerAnonymous {
  def oncePerSecond(callback: () => Unit): Unit = {
    while (true) { callback(); Thread.sleep(1000) }
  }
  def main(args: Array[String]): Unit = {
    oncePerSecond(() =>
      println("time flies like an arrow..."))
  }
}
```

本例中匿名函数通过右箭头 `=>` 将函数参数与主体分开，与 Java 的细箭头 `->` 略有不同。本例中参数为空，所以在剪头左侧放置空括号。函数主体与上面的 `timeFlies` 相同。

## Class

Scala 是面向对象语言，自然也有类。Scala 的类定义语法与 Java 类似。一个重要的区别是 Scala 的类可以有参数。例如：

```scala
class Complex(real: Double, imaginary: Double) {
  def re() = real
  def im() = imaginary
}
```

`Complex` 类有两个参数，即 `real` 和 `imaginary`。创建 `Complex` 类的实例必须传递这两个参数，例如：

```scala
new Complex(1.5, 2.3)
```

该类包含两个方法，即 `re` 和 `im`，用于访问这两部分。

需要注意的是，这两个方法的返回类型并没有明确给出。编译器会自动推断，它查看这两个方法的右侧并推断它们都返回 `Double` 类型的值。

> [!IMPORTANT]
>
> 如果实现发生变化，方法的推断结果类型也可能变化。因此，最佳实践是为类的公共成员指定显式结果类型。

对方法中的 local 值，鼓励使用推断类型。何时使用自动推断类型，何时显式指定类型，使用多了自然就明白。

### 无参方法

方法 `re` 和 `im` 的一个小问题是，为了调用它们，必须在它们的名称后面放置一对空的括号，如下所示：

```scala
object ComplexNumbers {
  def main(args: Array[String]): Unit = {
    val c = new Complex(1.2, 3.4)
    println("imaginary part: " + c.im())
  }
}
```

如果能像访问字段一样访问 `real` 和 `imaginary`，而不用添加空括号，就更好了。这在 scala 中是可行的，只需将它们定义为无参数的方法即可。无参数的方法与零参数方法的不同之处在于，它们的名称后面没有括号，不管是在定义还是使用中都没有。下面重写 `Complex` 类：

```scala
class Complex(real: Double, imaginary: Double) {
  def re = real
  def im = imaginary
}
```

### 继承和覆盖

Scala 中的所有类都从超类继承。当未指定超类时，隐式继承 `scala.AnyRef`。

在 Scala 中，可以重写从超类继承的方法。但是，必须使用 `override` 修饰符明确指定重写另一个方法，以避免意外重写。例如，`Complex` 类可以通过重新定义从 `Object` 继承的 `toString` 方法进行扩充：

```scala
class Complex(real: Double, imaginary: Double) {
  def re = real
  def im = imaginary
  override def toString() =
    "" + re + (if (im >= 0) "+" else "") + im + "i"
}
```

可以调用重写的 `toString` 方法：

```scala
object ComplexNumbers {
  def main(args: Array[String]): Unit = {
    val c = new Complex(1.2, 3.4)
    println("Overridden toString(): " + c.toString)
  }
}
```

## 代数数据类型和模式匹配

程序中常出现 tree 数据结构。例如，解释器和编译器通常在内部将程序表示为 tree；JSON 负载是 tree；由几种容器是 tree，如红黑树。

现在，我们通过一个小型计算器程序来研究如何在 Scala 中表示和操作此类 tree。该程序的目的是操作简单的算术表达式，包括加和、整数常量和变量。如 `1+2`、`(x+x)+(7+y)`。

首先，必须决定这些表达式的表示形式。最自然的形式是 tree，其中 node 是操作，leaf 是值。

在 java 引入 record 之前，java 可以将这种 tree 定义为抽象类，每个 node 或 leaf 是一个具体自雷。在函数式编程语言中，可以使用代数数据类型（algebraic data-type, ADT）达到同样的目的。

Scala 2 提供了介于两者之间的 `case` 类。使用它们来定义 tree 的类型：

```scala
abstract class Tree
object Tree {
  case class Sum(left: Tree, right: Tree) extends Tree
  case class Var(n: String) extends Tree
  case class Const(v: Int) extends Tree
}
```

`Sum`, `Var` 和 `Const` 被声明为 case 类，表示它们与标准类有所不同：

- 创建这些类的实例不需要使用 `new` 关键字，例如，可以写 `Tree.Const(5)` 而非 `new Tree.Const(5)`
- 自定为构造函数的参数定义了 getter 函数，即通过 `c.v` 就可以方位 `Tree.Const` 类的某个实例 `c` 的 `v` 参数值
- 提供了 `equals` 和 `hashCode` 的默认定义
- 提供 `toString` 的默认定义
- 这些类的实例可以通过模式匹配进行分解

现在定义好了算术表达式的数据类型，下面开始定义操作。从一个函数开始，该函数用于在某些环境中求表达式的值。环境的目的是为变量赋值。例如，假设环境为 `x` 赋值为 `5`，即 `{x -> 5}`，此时表达式 `x+1` 的结果为 `6`。

现在，必须找到一种方法表示环境。可以使用一些关联数据结构，如哈希表，但也可以直接使用函数。环境实际上是将值与变量关联的函数。上面给出的环境 `{x -> 5}` 在 Scala 中可以写为：

```scala
type Environment = String => Int
val ev: Environment = { case "x" => 5 }
```

该符号定义了一个函数，当给定字符串 `"x"` 为参数，返回整数 5，否则抛出异常。

上面定义了一个名为 `Environment` 的类型别名，它比普通函数类型 `String => Int` 可读性更强，使用也更方便。

现在可以给出求值函数的定义：

- `Sum` 的值是两个内部表达式求值结果之和
- `Var` 的值是通过环境中查找其内部名称获得
- `Const` 的值是其内部值本身

下面通过 tree 值 `t` 的模式匹配定义：

```scala
import Tree._

def eval(t: Tree, ev: Environment): Int = t match {
  case Sum(left, right) => eval(left, ev) + eval(right, ev)
  case Var(n)    => ev(n)
  case Const(v)  => v
}
```

可以按如下方式理解模式匹配的含义：

1. 它首先检查 tree `t` 是否为 `Sum`，如果是，它将 left sub-tree 赋值给变量 `left`，将 right sub-tree 赋值给变量 `right`，然后继续计算箭头后面的表达式的值，该表达式可以利用箭头左侧模式绑定的变量 `left` 和 `right`
2. 如果第一次检查失败，即如果 tree 不是 `Sum`，它继续检查 `t` 是否为 `Var`，如果是，将 `Var` 包含的名称赋值给变量 `n`，然后继续执行右侧表达式
3. 如果第二次检查也失败的，即 `t` 也不是 `Var` 类型，它会检查 `t` 是否是 `Const`，如果是，它将 `Const` 节点的值赋值给变量 `v`，然后继续执行右侧的操作
4. 最后，如果所有检查都失败了，则会引发异常来表示模式匹配失败；只有在声明了更多 `Tree` 子类时才会发生这种情况。

模式匹配的基本思想是尝试将一个值与一系列模式进行匹配，并且一旦模式匹配成功，就提取并命名该值的各个部分，最终评估这些命名部分的代码。

### 对比 OOP

熟悉面向对象范式的程序员可能奇怪为什么要在 `Tree` 范围之外定义一个 `eval` 函数，而不是在 `Tree` 中创建 `eval` 和抽象方法，并且在 `Tree` 的子类中提供覆盖实现。

实际上可以这么做，这是一种选择，只是模式匹配的可扩展性更强：

- 使用方法重写时，为 `Tree` 添加新的操作意味着对代码进行大量更改，因为需要在 `Tree` 的所有子类添加方法；但是，添加新的子类只需要在一个地方实现
- 使用模式匹配时，情况正好相反：添加新类型的节点需要修改所有在 tree 上模式匹配的函数，以将新节点考虑在内；另外，添加新操作只需要在一个地方定义函数。如果你的数据结构具有一组稳定的节点，则使用 ADT 和模式匹配更合适。

### 添加新操作



## 参考

- https://docs.scala-lang.org/tutorials/scala-for-java-programmers.html