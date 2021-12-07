package com.example.routing.client

import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import java.io.File

const val path = "static-slug"

fun Application.configureClientRouting() = routing {

    val staticRootFolder = File("../client/dist/client").canonicalFile
    val rootFileAbsolute = staticRootFolder.resolve(File("index.html"))

    get("{$path...}") {
        val slug = (call.parameters.getAll(path))?.joinToString(File.separator)
        val relativeFile = slug?.let { staticRootFolder.combineSafe(slug) }

        val file = if (relativeFile?.isFile == true) relativeFile else rootFileAbsolute
        call.respond(LocalFileContent(file))
    }
}