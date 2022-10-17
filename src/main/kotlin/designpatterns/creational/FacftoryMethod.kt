package designpatterns.creational

import java.lang.IllegalArgumentException

val notations = listOf("qa1", "km8")

fun main() {
    chessPiecesFactory(notations)
}

sealed class ChessPiece(type: Char, positionR: Char, positionC: Char) {
    data class King(val type: Char, val positionRow: Char, val positionColumn: Char) : ChessPiece(type, positionRow, positionColumn)
    data class Queen(val type: Char, val positionRow: Char, val positionColumn: Char) : ChessPiece(type, positionRow, positionColumn)
}

// Factory Method
fun chessPiecesFactory(notations: List<String>) {
    notations.forEach { notation ->
        val (type, positionR, positionC) = notation.toCharArray()
        when (type) {
            'q' -> ChessPiece.Queen(type = type, positionRow = positionR, positionColumn = positionC).printLocation()
            'k' -> ChessPiece.King(type = type, positionRow = positionR, positionColumn = positionC).printLocation()
            else -> throw IllegalArgumentException("unkown type received = $type")
        }
    }
}

fun ChessPiece.printLocation() {
    when (this) {
        is ChessPiece.King -> println("King is @ row = $positionRow column = $positionColumn")
        is ChessPiece.Queen -> println("Queen is @ row = $positionRow column = $positionColumn")
    }
}
