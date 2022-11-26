package designpatterns.behavioral

/**
 * An object takes up the responsibility to decide on all decisions
 * and make the communication with the rest of the objects involved in the process
 */

interface Employee {
    fun isOnHoliday(): Boolean
}

interface QA {
    fun test(): Boolean
}

object Rohit : QA, Employee {

    override fun isOnHoliday() = true
    override fun test() = false
    fun isApproved() = false
}

object Adam : QA, Employee {

    override fun isOnHoliday() = false

    override fun test() = true

}

interface Dev {
    fun fixCode(): Boolean
}

object Nila : Dev, Employee {

    override fun fixCode(): Boolean {
        return true
    }

    override fun isOnHoliday() = false

}

object Jason : Dev, Employee {
    override fun fixCode(): Boolean {
        return true
    }

    override fun isOnHoliday() = false

}

interface Manager {
    fun isAllGood(): Boolean
}

/**
 * Mediator
 */
object Charith : Manager {

    val qa1 = Rohit
    val qa2 = Adam
    val dev = Jason

    val leadDev = Nila

    fun askToFixCode(): Boolean {
        return leadDev.fixCode()
    }

    fun approvalFromSenior(): Boolean {
        return qa1.isApproved()
    }

    override fun isAllGood(): Boolean {
        if (!qa1.isOnHoliday()) {
            if (qa1.test()) {
                println("manager telling - release the app")
                return true
            } else {
                println("manager telling - can't release the app")
                return askToFixCode()
            }
        } else {
            if (!qa2.isOnHoliday() && approvalFromSenior()) {
                if (qa2.test()) {
                    println("manager telling - release the app")
                    return true
                } else {
                    println("manager telling - can't release the app")
                    return false
                }
            } else {
                println("manager telling - can't release the app")
                return false
            }
        }
    }
}

fun main() {
    val company = Company
    company.releaseApp()
}

object Company {

    val manager = Charith

    fun releaseApp(): Boolean {
        return manager.isAllGood().also { if (it) println("company can do a release") else println("release blocked") }
    }
}