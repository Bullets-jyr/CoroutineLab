package ch06.test4

import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    val sendChannel = actor<Int> {
        consumeEach {
            println("receive : $it")
        }
    }

    val job = launch {
        repeat(3){
            delay(100)
            sendChannel.send(it)
        }
    }
    job.join()
    sendChannel.close()
    println("main end")
}