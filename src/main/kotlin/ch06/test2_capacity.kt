package ch06.test2

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    val channel = Channel<Int>(capacity = 2, onBufferOverflow = BufferOverflow.DROP_OLDEST )

    launch {
        repeat(5) {
            channel.send(it)
            println("send $it")
            delay(100)
        }
        channel.close()
    }

    for (data in channel) {
        println("receive $data")
        delay(300)
    }
}

