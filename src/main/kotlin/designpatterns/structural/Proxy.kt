package designpatterns.structural

import java.net.URL

/**
 * Proxy design pattern helps to extends an object functionality with deciding what to do
 */
data class Images(
    val imageUrl: String
) {
    val image: ByteArray by lazy {
        println("calling lazy delegation...")
        URL(imageUrl).readBytes()
    }
}

fun main() {
    val imgUrl = Images("http://www.google.com")
    imgUrl.image
    imgUrl.image
    imgUrl.image
    imgUrl.image
    imgUrl.image
}