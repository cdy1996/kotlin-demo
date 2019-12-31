package com.cdy.kotlin.demo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) = runBlocking<Unit> {
    postItem(Item())

    Thread.sleep(1000L)
}


class Item() {

}

class Token() {

}

class Post(token: Token, item: Item) {
    override fun toString(): String {
        return "Post()"
    }
}


fun postItem(item: Item) {
    GlobalScope.launch {
        val token = requestToken()
        val post = createPost(token, item)
        processPost(post)
    }
}

suspend fun requestToken(): Token {
    println("${Thread.currentThread().name} requestToken")
    return Token()
}   // 挂起函数

suspend fun createPost(token: Token, item: Item): Post {
    println("${Thread.currentThread().name} createPost")
    return Post(token, item)
}  // 挂起函数

fun processPost(post: Post) {
    println("${Thread.currentThread().name} processPost ${post.toString()}")
}