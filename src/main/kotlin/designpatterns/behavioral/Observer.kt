package designpatterns.behavioral


/**
 * This pattern is about there will be a publisher publishing events to subscribers. The subscribers who are subscribed to the publisher will be receiving those events.
 * The subscribers can also unsubscribe from the publisher.
 * This pattern works with a subscribe/unsubscribe model.
 */
class Subscriber1 {
    fun receive(message: String) {
        println("sb1 - received message from publisher = $message")
    }
}

class Subscriber2 {
    fun receive(message: String) {
        println("sb2 - received message from publisher = $message")
    }
}

class Subscriber3 {
    fun receive(message: String) {
        println("sb3 - received message from publisher = $message")
    }
}

class Publisher {

    private val receivers = mutableMapOf<(String) -> Unit, (String) -> Unit>() // map (key, value)

    fun subscribe(receiver : (String) -> Unit) {
        receivers[receiver] = receiver
    }

    fun unsubscribe(receiver: (String) -> Unit) {
        receivers.remove(receiver)
    }

    fun publish() {
        for (r in receivers.values) {
            r("this is the message from the Publisher!!")
        }
    }

}

fun main() {
    val sub1 = Subscriber1()
    val sub2 = Subscriber2()
    val sub3 = Subscriber3()

    val publisher = Publisher()

    publisher.subscribe(sub1::receive)
    publisher.subscribe(sub2::receive)
    publisher.subscribe(sub3::receive)

    publisher.unsubscribe(sub3::receive)

    publisher.publish()
}