package ch04

import kotlinx.coroutines.*

fun main() = runBlocking{
    println("main start... ")
    val job = launch(Dispatchers.IO) {
        try {
            var start = System.currentTimeMillis()
            var i = 0
            while (i < 5) {
                if (isActive) {
                    if (System.currentTimeMillis() >= start) {
                        i++
                        println("coroutine : $i")
                        start += 500
                    }
                } else {
                    throw CancellationException()
                }
            }
        } finally {
            withContext(NonCancellable) {
                repeat(3) {
                    println("coroutine finally $it")
                    delay(100)
                }
            }
        }
    }
    delay(1000)
    println("main : cancel coroutine..")
    job.cancelAndJoin()
    println("main end... ")
}