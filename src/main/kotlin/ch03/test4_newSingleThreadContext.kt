package ch03.test4

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    listOf("one", "two", "three").forEachIndexed { index, value ->
        launch(newSingleThreadContext("myThread $value")) {
            println("coroutine $value start : ${Thread.currentThread().name}")
            delay(100L + index * 100L)
            println("coroutine $value end : ${Thread.currentThread().name}")
        }
    }
    delay(3000)
}
