
// Define shop - It has its name and list of fruits sold
data class Shop(val name: String, val fruits: List<String>)

fun main() {
    // shop instances
    val charithShop = Shop("Charith", listOf("apple", "banana"))
    val sajeeShop = Shop("Shajee", listOf("apple", "kiwi"))
    val paviShop = Shop("Pavi", listOf("banana", "mango"))

    // all shops
    val shops = listOf(charithShop, sajeeShop, paviShop)

    // how to find all apples from all shops?
    val appleCount = shops.flatMap { it.fruits }.count { it == "apple" }
    println("appleCount = $appleCount")

    // find all shops selling apple
    val appleShops = shops.filter { it.fruits.contains("apple") }.map { it.name }
    println("appleShops = $appleShops")

    // find shops not selling apple
    val noAppleShops = shops.filterNot { it.fruits.contains("apple") }.map { it.name }
    println("noAppleShops = $noAppleShops")

    /**
     * TODO
     * Exercise - List all types of fruits sold from all shops and count how many?
     * The result should be printed as: [apple, banana, kiwi, mango]
     * And the count will be: 4 (in our case)
     */
}

