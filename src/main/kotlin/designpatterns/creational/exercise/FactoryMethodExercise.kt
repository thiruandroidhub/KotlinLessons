package designpatterns.creational.exercise

/**
 * Exercise:Factory Method
 *
 * Given city names (Colombo, London, Paris) create a factory method to create the corresponding Countries
 * and print the city names with it - using the Factory Method Design Pattern.
 *
 * Out put will be something similar to:
 * Colombo is in Srilanka
 * London is in UK
 * Paris is in France
 */

val cities = listOf("Colombo", "London")

sealed class Country(cityName: String) {
    data class SriLanka(val cityName: String) : Country(cityName)
    data class UK(val cityName: String) : Country(cityName)
}

fun countryFactory(cities: List<String>) {
    cities.forEach { city ->
        when (city) {
            "Colombo" -> Country.SriLanka(city).printCityName()
            "London" -> Country.UK(city).printCityName()
        }
    }
}

fun Country.printCityName() {
    when (this) {
        is Country.SriLanka -> println("$cityName is in Srilanka")
        is Country.UK -> println("$cityName is in UK")
    }
}

fun main() {
    countryFactory(cities)
}