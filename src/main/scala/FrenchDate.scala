import java.time.LocalDate
import java.time.format.{DateTimeFormatter, FormatStyle}
import java.util.Locale

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Feb 2025, 5:50 PM
 */
object FrenchDate {
  def main(args: Array[String]): Unit = {
    val now = LocalDate.now();
    val df = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
      .withLocale(Locale.FRANCE)
    println(df.format(now))
  }
}
