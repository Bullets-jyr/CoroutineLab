package ch03.test7

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{

    listOf("one", "two", "three").forEachIndexed { index, value ->
        launch(Dispatchers.Unconfined) {
            println("coroutine $value start : ${Thread.currentThread().name}")
            delay(100L + index*100L)
            println("coroutine $value end : ${Thread.currentThread().name}")
        }
    }
    delay(3000)
}