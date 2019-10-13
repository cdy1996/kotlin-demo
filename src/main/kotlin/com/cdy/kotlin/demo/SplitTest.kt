package com.cdy.kotlin.demo

//解构
class User(var age: Int, var name: String) {
    // 用于放入解构的对象
    operator fun component1() = age
    operator fun component2() = name
}


fun main(args: Array<String>) {
//    val user = User(12, "name")
//    val (age, name) = user
//    print(age)
//    print(name)
//
//
//    val map = mapOf<String, String>("key" to "key", "value" to "value")
//    // 通过解构 来实现 遍历
//    for ((k, v) in map) {
//        println("$k -- $v")
//
//    }

    testLoop()
}


fun testLoop(){
    for (i in 1..10 step 2) {
        print(i)
    }

    val list = arrayListOf<String>("a", "b", "c", "d")
    for (s in list) {
        print("${s}")
    }

    for ((index,str) in list.withIndex()) {
        print("第${index}个元素是$str")
    }

    repeat(10) {
        print(it)
    }
}


fun testStream(){

    val list = arrayListOf<Char>('a', 'b', 'c', 'd')

    val map = list.map { it - 'a' }
    val find = map
            .filter {
                it > 0 }
            .find { it > 1 }



}