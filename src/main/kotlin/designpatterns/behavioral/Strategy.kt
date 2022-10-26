package designpatterns.behavioral

/**
 * Strategy design pattern allows to alter an object behaviour at run time with applying a certain strategy
 */
object WeaponStrategy {
    fun gunOne() {
        println("strategy for gun 1")
    }

    fun gunTwo() {
        println("strategy for gun 2")
    }

    fun gunThree() {
        println("strategy for gun 3")
    }
}

class Shooter {
    var currentStrategy = WeaponStrategy::gunOne // assign a function to a variable in Kotlin

    val shoot: () -> Unit = fun() {
        currentStrategy()
    }

    fun shoot() {
        currentStrategy()
    }
}

fun main() {
    val shooter = Shooter()
    shooter.shoot()

    shooter.apply {
        currentStrategy = WeaponStrategy::gunThree // applying a strategy of choice - gunThree
        shoot()
    }
}