package com.example.utils

import kotlin.math.abs

fun Int.wrap(min: Int, max: Int): Int =
    if (this < min) (max - abs(this)).wrap(min, max) else if (this >= max) (this % max).wrap(min, max) else this

fun Any?.asInt(): Int? {
    return this.toString().toIntOrNull()
}