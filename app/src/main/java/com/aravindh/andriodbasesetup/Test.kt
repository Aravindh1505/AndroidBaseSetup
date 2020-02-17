package com.aravindh.andriodbasesetup


/**
 *Created by Aravindh S on 16-02-2020.
 */

fun main() {

    printMe(2)
}

fun printMe(x: Int) {

    for (i in x..5) {
        println(i)
        printMe(i)
    }

}