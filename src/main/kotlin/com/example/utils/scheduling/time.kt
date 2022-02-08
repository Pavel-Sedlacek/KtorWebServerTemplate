package com.example.utils.scheduling

fun Int.hToMillis(): Long {
    return (this * 60).minToMillis()
}

fun Int.minToMillis(): Long {
    return (this * 60).sToMillis()
}

fun Int.sToMillis(): Long {
    return this * 1000L
}