package ch02

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    listOf("one", "two", "three").forEachIndexed { index, value ->
        launch(Dispatchers.Default) {
            println("coroutine..$value start : ${Thread.currentThread().name}")
//            Thread.sleep(100L + index*100L)
            delay(100L + index*100L)
            println("coroutine..$value end : ${Thread.currentThread().name}")
        }
    }
    Thread.sleep(2000)
}

//coroutine..one start : DefaultDispatcher-worker-1
//coroutine..two start : DefaultDispatcher-worker-2
//coroutine..three start : DefaultDispatcher-worker-3
//coroutine..one end : DefaultDispatcher-worker-1
//coroutine..two end : DefaultDispatcher-worker-2
//coroutine..three end : DefaultDispatcher-worker-3

//coroutine..one start : DefaultDispatcher-worker-1
//coroutine..two start : DefaultDispatcher-worker-2
//coroutine..three start : DefaultDispatcher-worker-3
//coroutine..one end : DefaultDispatcher-worker-1
//coroutine..two end : DefaultDispatcher-worker-1
//coroutine..three end : DefaultDispatcher-worker-1