package com.aravindh.andriodbasesetup.core

class MultiThreadingDemo : Thread() {

    override fun run() {
        println("currentThread id : ${currentThread().id}")
    }

}

fun main() {

    for (i in 1..100) {
        val multiThreadingDemo = MultiThreadingDemo()
        multiThreadingDemo.start()
    }


}