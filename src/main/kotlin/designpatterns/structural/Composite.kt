package designpatterns.structural

/**
 * Composite class to handle a list of objects which can do the same behaviour
 */
class CompositeSpaceRobots(
    private val spaceRobots: List<SpaceRobot>
) : Robot {
    override fun walk(x: Int, y: Int) {
        for (r in spaceRobots) {
            r.walk(x, y)
        }
    }

    override fun attack(x: Int, y: Int) {
        for (r in spaceRobots) {
            r.attack(x, y)
        }
    }
}

fun main() {
    val marsRobot = SpaceRobot(FastLeg(), Tank())
    val neptuneRobot = SpaceRobot(FastLeg(), Tank())

// Don't do this, just use the composite class to do the walk and attack
//    marsRobot.walk(1, 4)
//    neptuneRobot.walk(1, 4)
//
//    marsRobot.attack(4, 5)
//    neptuneRobot.attack(4, 5)

    val allSpaceRobots = CompositeSpaceRobots(listOf(marsRobot, neptuneRobot))
    allSpaceRobots.walk(1, 4)
    allSpaceRobots.attack(4, 5)
}