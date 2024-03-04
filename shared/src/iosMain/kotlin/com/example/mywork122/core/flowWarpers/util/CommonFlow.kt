package com.example.mywork122.core.flowWarpers.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

actual open class CommonFlow<T> actual constructor(
    private val flow: Flow<T>
    //we will delagate the flow to regular flow as well as in ios but we will implement the action here
): Flow<T> by flow {

    fun subscribe(
        coroutineScope: CoroutineScope,
        dispatcher: CoroutineDispatcher,
        onCollect: (T) -> Unit
    ): DisposableHandle {
        val job = coroutineScope.launch(dispatcher) {
            flow.collect(onCollect)
        }
        return DisposableHandle { job.cancel() }
    }

    fun subscribe(
        onCollect: (T) -> Unit
    ): DisposableHandle{
      return subscribe(
            coroutineScope = GlobalScope,
            dispatcher = Dispatchers.Main,
           onCollect = onCollect
        )
    }

}