package designpatterns.creational

// Using a data class with default parameters and named arguments we can achieve the Builder design pattern
// Here the firstName and lastName are madatory and the rest of the properties are optional as they are set with default params
data class User(
    val firstName: String,
    val lastName: String,
    val age: Int = 0,
    val phone: Int = 0,
    val address: String = ""
)

fun main() {
    val user1 = User(firstName = "jason", lastName = "calvert", age = 50, phone = 98798, address = "123 oiuh")
    val user2 = User("jason", "calvert", 50)

}