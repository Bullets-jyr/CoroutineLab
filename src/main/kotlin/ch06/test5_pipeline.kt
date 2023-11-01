package ch06.test5

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.somethingA() = produce<Int>{
    var x = 1
    while (true){
        delay(100)
        send(x++)
    }
}
fun CoroutineScope.somethingB(receiveChannel: ReceiveChannel<Int>) = produce<Int>{
    receiveChannel.consumeEach {
        send(it * it)
    }
}
fun main() = runBlocking {
    val job = launch {
        val receiveChannelA = somethingA()
        val receiveChannelB = somethingB(receiveChannelA)

        // job.cancel()을 하면 종료됨
        receiveChannelB.consumeEach {
            println("receive : $it")
        }
    }
    delay(500)
    job.cancel()
}