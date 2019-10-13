package com.cdy.kotlin.demo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch


var map = mutableMapOf<String, Channel<Any>>()


inline fun<reified T> T.post(){
    if (!map.containsKey(T::class.java.name)) {
        map.put(T::class.java.name, Channel())
    }
    GlobalScope.launch{
//        coroutineScope{
//
//        }
        launch {

        }
        map[T::class.java.name]?.send(this@post as Any)
    }
}

inline fun <T, reified R> T.onEvent(noinline action: suspend (R) -> Unit) {
    if (!map.containsKey(R::class.java.name)) {
        map.put(R::class.java.name, Channel())
    }
    GlobalScope.launch{
        while (true) {
            val receive = map[R::class.java.name]?.receive()
            GlobalScope.launch {
                action.invoke(receive as R)
            }
        }
    }
}


fun main(args: Array<String>) {
    "231".post()
    "231".post()

    var man = Man()
//    man.onEvent {str:String->
//        println(str)
//    }
//    man.test()
    man.test()
    Thread.sleep(5000L)
}

class Man {

    fun test(){
        onEvent { str:String ->
            println(str)


        }
    }
}