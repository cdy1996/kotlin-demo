package com.cdy.kotlin.demo

abstract class Parent(var a: String) {
    abstract fun onClick()

}

open class ClassTest(var int: Int, b: String) : Parent(b) {

    val age: Int = 0
        get() = field + int

    override fun onClick() {
    }

    init {
        println("init")
    }

    //模块作用返回
    internal fun ads() {

    }
}

fun main(args: Array<String>) {
    val test: ClassTest = ClassTest(321, "!23")
    test.a

    StringUtils.isEmpty("ewq")
    StringUtils.Companion.isEmpty("eqw")

    Zoo(Dog()).bark()
}


//伴生对象
class StringUtils {
    companion object {
        fun isEmpty(str: String): Boolean {
            return "" == str
        }
    }
}

//单例
class Single private constructor() {
    companion object {
        fun get(): Single {
            return Holder.instance
        }
    }

    // 内部对象
    private object Holder {
        val instance = Single()
    }
}


interface Animal {
    fun bark()
}

class Dog : Animal {
    override fun bark() {
        println("wa wa wa")
    }
}

// 代理对象
class Zoo(animal: Animal) : Animal by animal {

}
