package com.coverlettersai.plugins

import com.coverlettersai.boundary.request.CreateCoverLetterClientRequest
import com.coverlettersai.gateway.source.remote.OpenAIRemoteDataSource
import com.coverlettersai.gateway.source.repositories.OpenAIRepository
import com.coverlettersai.usecases.CoverLetterGenerationUseCase
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.gson.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import kotlin.text.Charsets
import io.ktor.client.plugins.contentnegotiation.*
fun Application.configureRouting() {
    routing {
        val clientHttp = HttpClient(CIO) {
            engine {
                requestTimeout = 0 // 0 to disable, or a millisecond value to fit your needs
            }
            install(ContentNegotiation) {
                gson()
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            Charsets {
                // Allow using `UTF_8`.
                register(Charsets.UTF_8)
            }
        }
        val openAIRepository = OpenAIRepository(OpenAIRemoteDataSource(clientHttp))


        route("/coverLetters"){
            post() {
                val request = call.receive<CreateCoverLetterClientRequest>()

                val coverLetterGenerationUseCase = CoverLetterGenerationUseCase(openAIRepository)
                val response = coverLetterGenerationUseCase.generateCoverLetter(request)

                call.respond(response)
            }
        }

    }
}
