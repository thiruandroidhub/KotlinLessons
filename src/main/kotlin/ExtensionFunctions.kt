data class UserCredentials(val password: String, val username: String)

fun main() {
    val credentials = UserCredentials("21890080983".hidePassword(), "thiru".hidePassword())

    println("the password is = ${credentials.password}")

    /**
     * TODO - Exercise: For these given cities, print the country names they belong to.
     *
     * The results should be printed as:
     * Batticaloa = SriLanka
     * London = UK
     *
     * Think of using an extension fun
     */
    val cityBatticaloa = "Batticaloa"
    val cityLondon = "London"

    cityBatticaloa.countryName()
    cityLondon.countryName()
}

fun String.hidePassword() = "*".repeat(length)

fun String.countryName() {
    when (this) {
        "Batticaloa" -> println("$this = Srilanka")
        "London" -> println("$this = UK")
    }
}