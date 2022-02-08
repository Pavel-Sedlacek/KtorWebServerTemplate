package com.example.utils.fs

fun String.extension() = this.substringAfterLast(".")
fun String.withoutExtension() = this.substringBeforeLast(".")