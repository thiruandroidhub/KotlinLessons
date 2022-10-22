package designpatterns.structural

/**
 * A facade will be an object to get access to a complex system
 */
class ComplexCache(
    private val filePath: String
) {
    private val internalStore = mutableMapOf<String, String>()

    fun store(key: String, value: String) {
        internalStore[key] = value
    }

    fun get(key: String): String {
        return internalStore[key] ?: "unknown value..."
    }

    fun commit() {
        println("committing data...")
        // store all the data from internalStore to a DB such as Room
    }
}

data class User(val name: String)

// store using this cache storing a User
// UserRepository is the Facade in this case
class UserRepository(
    private val complexCache: ComplexCache
) {
    fun storeUser(user: User) {
        complexCache.store("USER_KEY", user.name)
    }

    fun getUser(key: String) = User(complexCache.get(key))
}

fun main() {
    val complexCache = ComplexCache("file///path/123")
}