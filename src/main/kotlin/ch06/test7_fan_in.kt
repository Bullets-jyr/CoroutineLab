package ch06.test7

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // 발행
    val oddChannel = produce<String> {
        var x = 0
        while (true) {
            x++
            delay(100)
            if(x % 2 != 0) {
                // 홀수
                send("Odd $x")
            }
        }
    }
    // 발행
    val evenChannel = produce<String> {
        var x = 0
        while (true) {
            x++
            delay(100)
            if(x % 2 == 0) {
                // 짝수
                send("Even $x")
            }
        }
    }

    // 수신하기 위한 채널
    val myChannel = Channel<String>()
    launch {
        oddChannel.consumeEach {
            myChannel.send(it)
        }
    }
    launch {
        evenChannel.consumeEach {
            myChannel.send(it)
        }
    }

    // 수신
    val job = launch {
        myChannel.consumeEach {
            println("receive : $it")
        }
    }
    delay(1000)
    job.cancel()
}