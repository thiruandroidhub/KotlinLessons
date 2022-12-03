package designpatterns.behavioral

/**
 * When we want to undo an object state to the previous state momento is the pattern used
 */
class ShoppingCart {

    var items = mutableListOf<String>()

    fun addItems(item: String) {
        items.add(item)
        if (items.size > MAX_ALLOWED_ITEMS) {
            items.removeFirst()
        }
    }

    fun printCart() {
        println("Items = $items")
    }

    inner class State(private val previousItems: List<String>) {
        fun restore() {
            items = previousItems.toMutableList() // reverting to the previous state
        }
    }

    fun saveState(): State {
        return State(items.toList())
    }

    fun undo(state: State) {
        state.restore()
    }

    companion object {
        private const val MAX_ALLOWED_ITEMS = 2
    }

}

fun main() {
    val cart = ShoppingCart()
    cart.addItems("Bread")
    cart.addItems("Milk")

    val previousState = cart.saveState() // momento
    cart.printCart()

    cart.addItems("Apple")
    cart.addItems("Vegetables")
    cart.printCart()

    cart.undo(previousState)
    cart.printCart()
}