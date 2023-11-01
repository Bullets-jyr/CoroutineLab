package ch06.test6

import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 여러 곳에서 수신
fun main() = runBlocking {
    // 발행
    val myChannel = produce<Int> {
        var x = 1
        while (true){
            delay(100)
            send(x++)
        }
    }
    // 수신
    val job1 = launch {
        myChannel.consumeEach {
            println("job1 receive : $it")
        }
    }
    // 수신
    val job2 = launch {
        myChannel.consumeEach {
            println("job2 receive : $it")
        }
    }
    delay(500)
    // 수신
    val job3 = launch {
        myChannel.consumeEach {
            println("job3 receive : $it")
        }
    }
    delay(600)
    myChannel.cancel()
}