package ch07.test2

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    flowOf(1, "hello", true)
        .collect {
            println("flowOf : $it")
        }

    listOf(1,2,).asFlow()
        .collect {
            println("list : $it ")
        }

    arrayOf(1,2).asFlow()
        .collect {
            println("array : $it")
        }
}