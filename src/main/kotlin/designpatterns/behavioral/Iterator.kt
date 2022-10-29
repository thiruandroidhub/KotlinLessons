package designpatterns.behavioral

/**
 * Iterator design pattern is to make an object iterable so that we can loop/iterate through
 */
class FruitBag(
    private val fruits: List<Fruit>
) : Iterator<Fruit> {

    private var index = 0

    override fun hasNext(): Boolean = index < fruits.size

    override fun next(): Fruit = fruits[index++] // post increment - read the value first then increment it
}

data class Fruit(val name: String)

fun main() {

    val fruits = listOf(Fruit("apple"), Fruit("banana"))

    val fruitBag = FruitBag(fruits)

    for (f in fruitBag) {
        println("fruit = $f")
    }
}