package designpatterns.structural


import java.lang.IllegalArgumentException

/**
 * Decorator design pattern is to add/extend behaviour to existing system without having complex class hierarchy
 * Or add behaviour dynamically
 *
 */

// Problem here is we are unnecessarily creating a complicated class hierarchy
open class Cart {
    private val items = mutableMapOf<String, String>()

    open fun searchItem(id: String): String = items[id] ?: "Unknown"

    open fun addItem(id: String, name: String) {
        items[id] = name
    }
}

class LoggingCart : Cart() {
    override fun searchItem(id: String): String {
        println("searching item = $id")
        return super.searchItem(id)
    }
}

class ValidatingCart : Cart() {
    override fun addItem(id: String, name: String) {
        if (name.length > 10 ) throw IllegalArgumentException("$name has more than 10 chars")
        super.addItem(id, name)
    }
}

class ValidatingAndLoggingCart : Cart() {
    override fun searchItem(id: String): String {
        println("searching item = $id")
        return super.searchItem(id)
    }

    override fun addItem(id: String, name: String) {
        if (name.length > 10 ) throw IllegalArgumentException("$name has more than 10 chars")
        super.addItem(id, name)
    }
}

interface ShoppingCart {
    fun searchItem(id: String): String
    fun addItem(id: String, name: String)

    /**
     * Can use the operator overloading here too with get and set
     * operator fun get(id: String): String
     * operator fun set(id: String, name: String)
     */
}


/**
 * Using the decorator or delegation design pattern we remove the unnecessary class hierarchy
 */
class DefaultShoppingCart : ShoppingCart {
    private val items = mutableMapOf<String, String>()

    override fun searchItem(id: String): String {
        return items[id] ?: "Unknown"
    }

    override fun addItem(id: String, name: String) {
        items[id] = name
    }
}

class ValidatingShoppingCart(
    private val defaultCart: ShoppingCart
) : ShoppingCart by defaultCart {

    override fun addItem(id: String, name: String) {
        require(name.length < 10) { "$name has more than 10 chars" }
        defaultCart.addItem(id, name)
    }

}

class LoggingShoppingCart(
    private val defaultCart: ShoppingCart
) : ShoppingCart by defaultCart {

    override fun searchItem(id: String): String {
        println("searching item = $id")
        return defaultCart.searchItem(id)
    }

}

fun main() {
    val defaultCart = DefaultShoppingCart()
    val validating = ValidatingShoppingCart(defaultCart)
    val logging = LoggingShoppingCart(defaultCart)
}

