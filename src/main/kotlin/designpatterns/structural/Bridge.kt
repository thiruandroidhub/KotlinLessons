package designpatterns.structural

/**
 * Bridging helps to avoid abusing inheritance, and create single classes as possible to keep the system as flat as possible
 * Bridging is used in conjunction with Dependency Injection
 */
interface Robot {
    fun walk(x: Int, y: Int)
    fun attack(x: Int, y: Int)
}

interface Leg {
    fun walk(x: Int, y: Int)
}

class FastLeg : Leg {
    override fun walk(x: Int, y: Int) {

    }
}

interface Weapon {
    fun attack(x: Int, y: Int)
}

class Tank : Weapon {
    override fun attack(x: Int, y: Int) {

    }
}

class SpaceRobot(
    private val leg: Leg,
    private val weapon: Weapon
) : Robot {
    override fun walk(x: Int, y: Int) {
        leg.walk(x, y)
    }

    override fun attack(x: Int, y: Int) {
        weapon.attack(x, y)
    }
}

fun main() {
    val marsRobot = SpaceRobot(FastLeg(), Tank())
    val neptuneRobot = SpaceRobot(FastLeg(), Tank())
}

// No need to create these class hierarchies.
// Provide/Bride the functionality we want which these objects have in common through other objects
//class MarsRobots : SpaceRobot() {
//    override fun walk(x: Int, y: Int) {
//    }
//}
//
//class NeptuneRobot : SpaceRobot() {
//    override fun walk(x: Int, y: Int) {
//    }
//}


class ResidentialRobot : Robot {
    override fun walk(x: Int, y: Int) {

    }

    override fun attack(x: Int, y: Int) {

    }

}