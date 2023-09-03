package ch02

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun suspendFun(no: Int): Int {
    var sum = 0
    for (i in 1..no) {
        delay(i * 10L)
        sum += i
    }
    return sum
}

fun main() = runBlocking {
    for (i in 1..3) {
        launch(Dispatchers.Default) {
            println("coroutine..$i start : ${Thread.currentThread().name}")
            val result = suspendFun(10)
            println("coroutine..$i end : ${Thread.currentThread().name}, result : $result")
        }
    }
    Thread.sleep(2000)
}