package ch01

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main step 1")
    val job = launch {
        var sum = 0
        for (i in 1..10) {
            delay(100)
            sum += i
        }
    }
    println("main step 2")
    job.join()
    println("main end")
}