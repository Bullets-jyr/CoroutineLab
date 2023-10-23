package ch06.test3

import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val receiveChannel = produce<Int> {
        repeat(3){
            send(it)
            delay(100)
        }
    }

    receiveChannel.consumeEach {
        println("receive $it")
    }
}