package designpatterns.behavioral

/**
 * The interpreter design pattern is used to write our oun DSL. The DSL is the Design Specific Language in a language in its own domain.
 * Like gradle, maven we use to build a project or xml layouts in android are the examples of a DSL. When writting our own DSL we can use the Interpreter design pattern.
 */
fun main() {
    // DSL
    val sql = select("name, age") {
        from("users") {
            where("age > 20")
        }
    }

    println(sql) // SELECT name, age FROM users WHERE age > 20

}

fun select(column: String, from: SelectClause.() -> Unit): SelectClause {
    return SelectClause(column).apply(from)
}

class SelectClause(private val columnName: String) {

    lateinit var fromClause: FromClause

    fun from(tableName: String, where: FromClause.() -> Unit) {
        fromClause = FromClause(tableName).apply(where)
    }

    override fun toString() = "SELECT $columnName $fromClause"
}

class FromClause(private val tableName: String) {

    lateinit var whereClause: WhereClause

    fun where(condition: String) {
        whereClause = WhereClause(condition)
    }

    override fun toString() = "FROM $tableName $whereClause"
}

class WhereClause(private val condition: String) {
    override fun toString() = "WHERE $condition"
}