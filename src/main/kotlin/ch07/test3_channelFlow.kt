package ch07.test3

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow

val helloFlow = flow {
    repeat(3) {
        delay(100)
        emit("hello : $it")
    }
}

val worldFlow = flow {
    repeat(3) {
        delay(200)
        emit("world : $it")
    }
}

//fun <T> Flow<T>.flowMerge(other: Flow<T>): Flow<T> = flow {
//    collect { emit(it) }
//    other.collect { emit(it) }
//}

//Exception in thread "DefaultDispatcher-worker-3" java.lang.IllegalStateException: Flow invariant is violated:
//Emission from another coroutine is detected.
//Child of StandaloneCoroutine{Active}@4a2cf79f, expected child of BlockingCoroutine{Active}@149a7ab5.
//FlowCollector is not thread-safe and concurrent emissions are prohibited.
//To mitigate this restriction please use 'channelFlow' builder instead of 'flow'
//at kotlinx.coroutines.flow.internal.SafeCollector_commonKt$checkContext$result$1.invoke(SafeCollector.common.kt:69)
//at kotlinx.coroutines.flow.internal.SafeCollector_commonKt$checkContext$result$1.invoke(SafeCollector.common.kt:26)
//at kotlin.coroutines.CoroutineContext$Element$DefaultImpls.fold(CoroutineContext.kt:70)
//at kotlinx.coroutines.Job$DefaultImpls.fold(Job.kt:112)
//at kotlinx.coroutines.JobSupport.fold(JobSupport.kt:25)
//at kotlin.coroutines.CombinedContext.fold(CoroutineContextImpl.kt:131)
//at kotlinx.coroutines.flow.internal.SafeCollector_commonKt.checkContext(SafeCollector.common.kt:26)
//at kotlinx.coroutines.flow.internal.SafeCollector.checkContext(SafeCollector.kt:106)
//at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:83)
//at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:66)
//at ch07.test3.Test3_channelFlowKt$flowMerge$1$job1$1$1.emit(test3_channelFlow.kt:29)
//at kotlinx.coroutines.flow.internal.SafeCollectorKt$emitFun$1.invoke(SafeCollector.kt:15)
//at kotlinx.coroutines.flow.internal.SafeCollectorKt$emitFun$1.invoke(SafeCollector.kt:15)
//at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:87)
//at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:66)
//at ch07.test3.Test3_channelFlowKt$helloFlow$1.invokeSuspend(test3_channelFlow.kt:11)
//at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
//at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:108)
//at kotlinx.coroutines.internal.LimitedDispatcher$Worker.run(LimitedDispatcher.kt:115)
//at kotlinx.coroutines.scheduling.TaskImpl.run(Tasks.kt:103)
//at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:584)
//at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:793)
//at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:697)
//at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:684)
//Suppressed: kotlinx.coroutines.internal.DiagnosticCoroutineContextException: [StandaloneCoroutine{Cancelling}@4a2cf79f, Dispatchers.IO]
//Exception in thread "DefaultDispatcher-worker-3" java.lang.IllegalStateException:           Flow exception transparency is violated:
//Previous 'emit' call has thrown exception java.lang.IllegalStateException: Flow invariant is violated:
//Emission from another coroutine is detected.
//Child of StandaloneCoroutine{Active}@4a2cf79f, expected child of BlockingCoroutine{Active}@149a7ab5.
//FlowCollector is not thread-safe and concurrent emissions are prohibited.
//To mitigate this restriction please use 'channelFlow' builder instead of 'flow', but then emission attempt of value 'world : 0' has been detected.
//Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.
//For a more detailed explanation, please refer to Flow documentation.
//at kotlinx.coroutines.flow.internal.SafeCollector.exceptionTransparencyViolated(SafeCollector.kt:140)
//at kotlinx.coroutines.flow.internal.SafeCollector.checkContext(SafeCollector.kt:104)
//at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:83)
//at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:66)
//at ch07.test3.Test3_channelFlowKt$flowMerge$1$job2$1$1.emit(test3_channelFlow.kt:32)
//at kotlinx.coroutines.flow.internal.SafeCollectorKt$emitFun$1.invoke(SafeCollector.kt:15)
//at kotlinx.coroutines.flow.internal.SafeCollectorKt$emitFun$1.invoke(SafeCollector.kt:15)
//at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:87)
//at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:66)
//at ch07.test3.Test3_channelFlowKt$worldFlow$1.invokeSuspend(test3_channelFlow.kt:18)
//at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
//at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:108)
//at kotlinx.coroutines.internal.LimitedDispatcher$Worker.run(LimitedDispatcher.kt:115)
//at kotlinx.coroutines.scheduling.TaskImpl.run(Tasks.kt:103)
//at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:584)
//at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:793)
//at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:697)
//at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:684)
//Suppressed: kotlinx.coroutines.internal.DiagnosticCoroutineContextException: [StandaloneCoroutine{Cancelling}@4df9b20a, Dispatchers.IO]
//fun <T> Flow<T>.flowMerge(other: Flow<T>): Flow<T> = flow {
//    val job1 = CoroutineScope(Dispatchers.IO).launch {
//        collect { emit(it) }
//    }
//    val job2 = CoroutineScope(Dispatchers.IO).launch {
//        other.collect { emit(it) }
//    }
//    job1.join()
//    job2.join()
//}

fun <T> Flow<T>.flowMerge(other: Flow<T>): Flow<T> = channelFlow {
    val job1 = launch {
        collect { send(it) }
    }
    val job2 = launch {
        other.collect { send(it) }
    }
    job1.join()
    job2.join()
}

fun main() = runBlocking {
    helloFlow.flowMerge(worldFlow).collect {
        println(it)
    }
}