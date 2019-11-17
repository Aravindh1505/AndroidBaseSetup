package com.aravindh.andriodbasesetup

fun main() {

    val isPalindrome = checkIsPalindromeWithLoop("ABA")
    println("isPalindrome $isPalindrome")

}

fun checkIsPalindrome(str: String): Boolean {
    return StringBuilder(str).reverse().toString() == str
}

fun checkIsPalindromeWithLoop(str: String): Boolean {
    val length = str.length


    for (i in 0 until length) {
        if (str[i] != str[length - i - 1]) {
            return false
        }
    }
    return true
}