package com.coverlettersai.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        get("/greeting") {
            call.respondText("Hello World!")
        }
    }
}
