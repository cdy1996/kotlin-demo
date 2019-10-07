package com.cdy.kotlin.demo

import kotlin.coroutines.*


val fibonacci = buildSequenece<Int> {
    yield(1)
    var cur = 1
    var next = 1
    while (true) {
        yield(next)
        val tmp = cur + next
        cur = next
        next = tmp
    }

}

fun main(args: Array<String>) {
    fibonacci.take(10).iterator().forEach {
        print("$it ")
    }
}


interface SequenceBuilder<in T> {
    suspend fun yield(value: T)
}

fun <T> buildSequenece(block: suspend SequenceBuilder<T>.() -> Unit): Sequence<T> = Sequence {
    MyCorountine<T>().apply {
        nextStep = block.createCoroutine(receiver = this, completion = this)
    }
}

private class MyCorountine<T> : AbstractIterator<T>(), SequenceBuilder<T>, Continuation<Unit> {
    override fun computeNext() {
        nextStep.resume(Unit)

    }

    override suspend fun yield(value: T) {
        setNext(value)
        return suspendCoroutine { cont->nextStep = cont }
    }

    override fun resumeWith(result: Result<Unit>) {
        if (result.isSuccess) {
            done()
        } else{
            if (result.exceptionOrNull()!=null) {
                throw result.exceptionOrNull()!!
            }

        }
    }

    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    lateinit var nextStep: Continuation<Unit>


}