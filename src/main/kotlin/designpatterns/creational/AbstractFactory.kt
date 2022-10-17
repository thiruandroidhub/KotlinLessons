package designpatterns.creational

/**
 * Requirement: Given the server configuration config create the classes needed to represent the ServerConfiguration
 * server configuration:
 * port = 900
 * environment = prod/test
 * address = http://123.50.900
 */

data class ServerConfiguration(val properties: List<Property>)
data class Property(val propertyMap: Map<String, String>)

// Abstract Factory (uses collection of other factory method/s)
fun createServerConfigFactory(configText: String): ServerConfiguration {
    val properties: MutableList<Property> = mutableListOf()
    val propertyList = configText.split(",")
    propertyList.forEach { propertyText ->
        val property = createPropertyFactory(propertyText) // calling other factory method to create Properties
        properties.add(property)
    }
    return ServerConfiguration(properties)
}

fun createPropertyFactory(propertyText: String): Property {
    val (name, value) = propertyText.split("=")
    return when (name) {
        "port", "environment", "address" -> Property(mapOf(name to value))
        else -> throw Exception("unknown")
    }
}

fun main() {
    val config = "port=900,environment=test,address=http://123.50.900"
    val serverConfig = createServerConfigFactory(config)
    println("server config = $serverConfig")
}