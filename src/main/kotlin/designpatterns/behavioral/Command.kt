package designpatterns.behavioral

/**
 * If an object (Robot) embeds an action (Walk) let a caller executes that action later with applying a command
 *
 * Here we are using lambdas as the commands to execute the action
 *
 * The 3 phases of a lambda is: define type of the lambda, implementation details, invoke it
 */
class Robot {

    private val commands = mutableListOf<Command>()

    // Action
    fun walk(x: Int, y: Int) {
        println("I am walking x=$x, y=$y")
    }

    fun addCommand(command: Command) {
        commands.add(command)
    }

    fun runAllCommands() {
        while (commands.isNotEmpty()) {
            val command = commands.removeFirst()
            command() // invoking the lambda
        }
    }
}


val generateCommands: (r: Robot, x: Int, y: Int) -> Command = { r, x, y -> { r.walk(x, y) } } // defining and implementing the lambda


typealias Command = () -> Unit

fun main() {
    val r = Robot()

    r.addCommand(generateCommands(r, 1, 1))
    r.addCommand(generateCommands(r, 2, 2))
    r.addCommand(generateCommands(r, 3, 3))

    r.runAllCommands()
}

