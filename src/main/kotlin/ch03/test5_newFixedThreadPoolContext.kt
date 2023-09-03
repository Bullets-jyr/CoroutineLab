package ch03.test5

import kotlinx.coroutines.*

fun main() = runBlocking {
    listOf("one", "two", "three").forEachIndexed { index, value ->
        launch(newFixedThreadPoolContext(2, "myThread $value")) {
            println("coroutine $value start : ${Thread.currentThread().name}")
            delay(100L + index * 100L)
            println("coroutine $value end : ${Thread.currentThread().name}")
        }
    }
    delay(3000)
}