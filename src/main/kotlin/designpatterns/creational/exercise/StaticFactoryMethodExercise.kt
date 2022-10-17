package designpatterns.creational.exercise

import java.math.BigDecimal

/**
 * Exercise: Static Factory Method
 *
 * We have been asked to design a class (Order.kt) to place an order with buy and sell prices. (The prices can be in BigDecimal)
 *
 * 1. Create an instance of class Order using the static factory method placeOrder(buyPrice: BigDecimal, sellPrice: BigDecimal)
 * 2. Restrict any constructor creations.
 * 3. Print the order placed.
 *
 * Printed output should look as:
 * Order is placed with buyPrice = 10 and sellPrice = 100
 *
 */
class Order private constructor(buyPrice: BigDecimal, sellPrice: BigDecimal) {
    init {
        println("Order is placed with buyPrice = $buyPrice and sellPrice = $sellPrice")
    }

    companion object {
        fun placeOrder(buyPrice: BigDecimal, sellPrice: BigDecimal): Order =
            Order(buyPrice = buyPrice, sellPrice = sellPrice)
    }
}

fun main() {
    Order.placeOrder(BigDecimal.TEN, BigDecimal.ONE)
}