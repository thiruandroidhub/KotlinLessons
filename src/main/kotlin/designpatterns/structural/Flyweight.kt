package designpatterns.structural

import java.io.File

/**
 * Flyweight objects are stateless objects and they stay light/less weight in memory
 * Here we are creating the object using our Singleton creational pattern and structuring the object as less weight using the flyweight structural pattern
 */
object RobotImages {
    val directionImages = listOf(
        File("up.png"),
        File("down.png"),
        File("left.png"),
        File("right.png")
    )
}

class GameRobot {

    // the default direction a robot is in when it is drawn on the game board
    private var direction = Direction.LEFT

    // access the flyweight object to get the direction images rather creating the list everytime when the robot object is created to save memory
    val directionImages = RobotImages.directionImages

    fun getDirectionImage() {
        when (direction) {
            Direction.UP -> directionImages[0]
            Direction.DOWN -> directionImages[1]
            Direction.LEFT -> directionImages[2]
            Direction.RIGHT -> directionImages[3]
        }
    }

    fun setDirection(directionToSet: Direction) {
        direction = directionToSet
    }
}

// UP, DOWN, LEFT, RIGHT
enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

fun main() {
    val r1 = GameRobot()
    r1.setDirection(Direction.UP)
    r1.getDirectionImage()

    val r2 = GameRobot()
    val r3 = GameRobot()
    val r4 = GameRobot()
}