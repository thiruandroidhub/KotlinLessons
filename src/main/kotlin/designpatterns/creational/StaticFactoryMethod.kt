package designpatterns.creational

import java.lang.IllegalArgumentException

// Example 1
class Server private constructor(port: Long) {
    init {
        println("server started in port = $port")
    }

    companion object {
        fun withPort(port: Long) = Server(port) // static factory method
    }
}


// Example 2
val loadedText = listOf("qa1", "km8")
sealed class Piece(type: Char, positionR: Char, positionC: Char) {
    data class King(val type: Char, val positionRow: Char, val positionColumn: Char) :
        Piece(type, positionRow, positionColumn)

    data class Queen(val type: Char, val positionRow: Char, val positionColumn: Char) :
        Piece(type, positionRow, positionColumn)

    companion object {
        // Static Factory Method
        fun create(type: Char, positionR: Char, positionC: Char) {
            when (type) {
                'q' -> Queen(type = type, positionRow = positionR, positionColumn = positionC).printLocation()
                'k' -> King(type = type, positionRow = positionR, positionColumn = positionC).printLocation()
                else -> throw IllegalArgumentException("unkown type received = $type")
            }
        }
    }
}

fun Piece.printLocation() {
    when (this) {
        is Piece.King -> println("King is @ row = $positionRow column = $positionColumn")
        is Piece.Queen -> println("Queen is @ row = $positionRow column = $positionColumn")
    }
}


fun main() {
    //val server = Server(8000) // won't compile - constructor is private
    val server = Server.withPort(800) // can only create Server withPort

    loadedText.forEach { notation ->
        val (type, positionR, positionC) = notation.toCharArray()
        Piece.create(type, positionR, positionC) // static factory method
    }
}
