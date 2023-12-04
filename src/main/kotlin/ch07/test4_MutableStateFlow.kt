package ch07.test4

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val stateFlow = MutableStateFlow(0)

suspend fun changeState(data: Int){
    stateFlow.emit(data)
//    stateFlow.value = data
}

fun main() = runBlocking{
    changeState(1)
    changeState(2)
    val job1 = launch {
        stateFlow.collect {
            println("job1 : $it")
        }
    }
    delay(100)
    changeState(3)
    delay(100)
    job1.cancel()
    changeState(4)
    changeState(5)
    val job2 = launch {
        stateFlow.collect {
            println("job2 : $it")
        }
    }
    delay(100)
    changeState(6)
    delay(100)
    job2.cancel()
}