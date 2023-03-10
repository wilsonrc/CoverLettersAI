package com.coverlettersai

import com.coverlettersai.plugins.configureHTTP
import com.coverlettersai.plugins.configureRouting
import com.coverlettersai.plugins.configureSecurity
import com.coverlettersai.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = "5000".toInt()) {
        configureHTTP()
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureSecurity()
    configureRouting()
}
