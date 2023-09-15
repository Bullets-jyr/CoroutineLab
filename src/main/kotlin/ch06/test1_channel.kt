package ch06.test1

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    val channel = Channel<Int>()

    launch {
        repeat(3) {
            delay(100)
            channel.send(it)
            println("send : $it")
        }
        // 중요
        channel.close()
    }

    // main thread에서 구독
//    repeat(3) {
//        delay(300)
//        println("receive ${channel.receive()}")
//    }
//    for(data in channel) == channel.consumeEach
//    for(data in channel) {
//        println("receive $data")
//    }
//    channel.consumeEach {
//        println("receive $it")
//    }
    println("main end")
}