package ch04

import kotlinx.coroutines.*

fun main() = runBlocking{
    val job1 = launch {
        println("job1...")
    }
    job1.cancel()

    val job2 = launch(start = CoroutineStart.ATOMIC) {
        println("job2... 1")
        delay(200)
        println("job2... 2")
    }
    job2.cancel()

    val job3 = launch(start = CoroutineStart.UNDISPATCHED, context = Dispatchers.IO) {
        println("job3... 1.. ${Thread.currentThread().name}")
        delay(200)
        println("job3... 2.. ${Thread.currentThread().name}")
    }
    job3.join()

    val job4 = launch(start = CoroutineStart.LAZY) {
        println("job4...")
    }
    delay(200)
    println("main step...")
    job4.start()
    job4.join()
}