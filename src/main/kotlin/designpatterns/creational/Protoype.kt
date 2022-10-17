package designpatterns.creational
/**
 * Prototyping design pattern is creating similar objects with slightly different behaviour
 */

// imagine we have users with roles
data class Employee(val name: String, val role: Role, val permission: Set<String>, private val colleagues: Set<String>)

enum class Role {
    ADMIN,
    IT,
    HR
}

val allEmployeesInDB = mutableListOf(
    Employee(
        name = "thiru",
        role = Role.IT,
        permission = setOf(
            "can access jira",
            "can access intranet"
        ),
        colleagues = setOf("charith", "jason")
    )
)

//fun createEmployeeLegacy(role: Role, name: String) {
//    for (employee in allEmployeesInDB) {
//        if (role == employee.role) {
//            allEmployeesInDB += Employee(
//                name = name,
//                role = role,
//                permission = employee.permission,
//                colleagues = employee.colleagues // breaking
//            )
//            break
//        }
//    }
//    println("print all employees in DB = $allEmployeesInDB")
//}

fun createEmployee(role: Role, name: String) {
    for (employee in allEmployeesInDB) {
        if (role == employee.role) {
            allEmployeesInDB += employee.copy(name = name)
            break
        }
    }
    println("print all employees in DB = $allEmployeesInDB")
}

fun main() {
    createEmployee(name = "jason", role = Role.IT)
}