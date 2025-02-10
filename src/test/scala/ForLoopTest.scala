import org.scalatest.funsuite.AnyFunSuite

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 08 Feb 2025, 9:45 AM
 */
class ForLoopTest extends AnyFunSuite {

  test("for loop") {
    val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
      "Scottish Terrier", "Great Dane", "Portuguese Water Dog")

    for (breed <- dogBreeds)
      println(breed)

    println()
    for (breed <- dogBreeds
         if breed.contains("Terrier"))
      println(breed)
  }

  test("for loop yield") {
    val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
      "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
    val filteredBreeds = for {
      breed <- dogBreeds
      if breed.contains("Terrier") && !breed.startsWith("Yorkshire")
    } yield breed

  }

  test("option") {
    val dogBreeds = List(Some("Doberman"), None, Some("Yorkshire Terrier"),
      Some("Dachshund"), None, Some("Scottish Terrier"),
      None, Some("Great Dane"), Some("Portuguese Water Dog"))
    println("first pass:")
    for {
      breedOption <- dogBreeds
      breed <- breedOption
      upcasedBreed <- breed.toUpperCase()
    } println(upcasedBreed)
  }

  test("nest for loop"){
    val lst = for{
      z <- 1 to 10
      a <- 11 to 13
      pair = (z,a)
    }yield pair
    println(lst)
  }

}
