package com.cdy.kotlin.demo

import com.fasterxml.jackson.databind.ObjectMapper

class Test<T> where T : com.cdy.kotlin.demo.Callback, T : Runnable {
    fun add(t: T) {
        t.run()
        t.callback()
    }
}

open class AAA : Runnable {
    override fun run() {
        println("run")
    }


}

class BBB : Callback, AAA() {
    override fun callback() {
        println("callback")
    }

}

interface Callback {
    fun callback()
}

fun main(args: Array<String>) {
    val test = Test<BBB>()
    test.add(BBB())

    fromJson<String>("213")
        .takeIf { it.equals("213") }
        ?.also { println(it) }
        ?: println("姓名为空")
}


inline fun <reified T> fromJson(json: String): T {
    val j = ObjectMapper()
    return j.readValue(json, T::class.java)
}
