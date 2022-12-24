package designpatterns.behavioral


// Schedule
// arrive at work
// coffee
// meetings / coding
// lunch
// meetings / coding
// go home

fun main() {
    // java way
    val mondayRoutine = MondayRoutine()
    mondayRoutine.runSchedule()

    // kotlin way
    runSchedule(
        doBeforeLunch = { println("do before lunch") },
        doAfterLunch = { println("do after lunch") }
    )
}

// Def of Template method from the web:
// Template method design pattern is to define an algorithm as a skeleton of operations and leave the details to be implemented by the child classes. The overall structure and sequence of the algorithm are preserved by the parent class.
// We can achieve the same with kotlin functions and lambdas as well as local functions
fun runSchedule(
    doBeforeLunch: () -> Unit,
    doAfterLunch: () -> Unit,
) {

    // local fun 1
    val coffee: () -> Unit = { println("coffee") }

    // local fun 2
    fun arriveToWork() {
        println("arrive to work")
    }

    // local fun 3
    fun lunch() {
        println("lunch")
    }

    // local fun 4
    val goHome = { println("go home") }

    arriveToWork()
    coffee()
    doBeforeLunch()
    lunch()
    doAfterLunch()
    goHome()
}

abstract class DayRoutine {
    abstract fun doBeforeLunch()
    abstract fun doAfterLunch()

    private fun arriveToWork() {
        println("arriving to work")
    }

    private fun haveCoffee() {
        println("coffee")
    }

    private fun lunch() {
        println("having lunch")
    }

    // Template method
    fun runSchedule() {
        arriveToWork()
        haveCoffee()
        doBeforeLunch()
        lunch()
        doAfterLunch()
        goHome()
    }

    private fun goHome() {
        println("go home")
    }
}

class MondayRoutine : DayRoutine() {
    override fun doBeforeLunch() {
        println("stuff i do before lunch")
    }

    override fun doAfterLunch() {
        println("stuff i do after lunch")
    }
}