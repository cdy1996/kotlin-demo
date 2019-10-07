package com.cdy.kotlin.demo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch


fun testChannel() {
    val c = Channel<Int>()

    GlobalScope.launch{
        get(c)
    }
    GlobalScope.launch{
        put(c)
    }
    Unit
}

fun main(args: Array<String>) {
    testChannel()
}

suspend fun get(channel: Channel<Int>){
    while (true) {
        println(channel.receive())
    }
}

suspend fun put(channel: Channel<Int>){
    var i=0
    while (true) {
        channel.send(i++)
    }
}


