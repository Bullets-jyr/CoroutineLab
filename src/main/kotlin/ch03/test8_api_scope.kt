package ch03.test8

import kotlinx.coroutines.*

fun main() = runBlocking{
    GlobalScope.launch {
        repeat(5){
            println("coroutine...")
        }
    }

    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        println(".....")
    }
}