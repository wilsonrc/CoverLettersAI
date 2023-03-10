package com.coverlettersai.plugins

import com.coverlettersai.boundary.request.CreateCoverLetterRequest
import com.coverlettersai.boundary.response.CreateCoverLetterResponse
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {
        route("/coverLetters"){
            post() {
                val request = call.receive<CreateCoverLetterRequest>()

                //TODO PROCESS REQUEST

                val response = CreateCoverLetterResponse(
                    linkedinUser = request.linkedinUser,
                    companyName = request.companyName,
                    coverLetters = listOf(),
                    createdAt = "2020-01-01"
                )

                call.respond(response)
            }
        }

    }
}
