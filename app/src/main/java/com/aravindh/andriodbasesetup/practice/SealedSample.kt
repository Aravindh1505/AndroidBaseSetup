package com.aravindh.andriodbasesetup.practice


/**
 *Created by Aravindh S on 17-02-2020.
 */

sealed class SealedSample {

    class January(val jan: String) : SealedSample()

    class February(val feb: Int, val x: Double) : SealedSample()

    class March(val feb: Double) : SealedSample()
}

fun main() {

    val feb = SealedSample.February(5, 6.0)
    months(feb)
}

fun months(sealedSample: SealedSample) {
    when (sealedSample) {
        is SealedSample.January -> println("This is January")
        is SealedSample.February -> println("This is February")
        is SealedSample.March -> println("This is March")
    }
}

