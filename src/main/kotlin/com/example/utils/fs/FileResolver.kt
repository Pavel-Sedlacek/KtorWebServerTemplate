package com.example.utils.fs

import java.io.File
import java.io.FileNotFoundException

object FileResolver {

    fun resolve(path: String): File? = File("res/upload/$path").let { if (it.exists()) it else null }

    fun fileNotFound(): File = resolve("not_found.png") ?: throw FileNotFoundException()

    fun somethingWentWrong(): File = resolve("something_went_wrong.png") ?: throw FileNotFoundException()
}


