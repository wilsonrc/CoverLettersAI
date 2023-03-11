package com.coverlettersai.gateway.source.remote

import com.coverlettersai.OPEN_AI_API_KEY
import com.coverlettersai.boundary.request.ChatGPTRequest
import com.coverlettersai.gateway.source.OpenAIDataSource
import com.coverlettersai.boundary.response.ChatGPTResponse
import com.coverlettersai.boundary.response.Choice
import com.coverlettersai.boundary.request.MessageRequest
import com.coverlettersai.boundary.response.Usage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class OpenAIRemoteDataSource(private val client: HttpClient) : OpenAIDataSource {
    override suspend fun getChatGPTResponse(chatGPTRequest: ChatGPTRequest): ChatGPTResponse {

        val response = client.request("https://api.openai.com/v1/chat/completions") {
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            headers {
                append("Authorization", "Bearer $OPEN_AI_API_KEY")
            }
            setBody(
                chatGPTRequest
            )
        }

        return try {
            println(response.status.value)
            response.body<ChatGPTResponse>()
        } catch (e: Exception) {
            println(e.message)
            return ChatGPTResponse(
                id = "id",
                `object` = "object",
                created = 0,
                model = "model",
                usage = Usage(
                    prompt_tokens = 0,
                    completion_tokens = 0,
                    total_tokens = 0
                ),
                choices = listOf(
                    Choice(
                        message = MessageRequest(
                            role = "role",
                            content = "content"
                        ),
                        finish_reason = "finish_reason",
                        index = 0
                    )
                )
            )
        }
    }
}