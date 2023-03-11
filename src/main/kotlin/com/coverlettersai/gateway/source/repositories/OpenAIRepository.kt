package com.coverlettersai.gateway.source.repositories

import com.coverlettersai.boundary.request.ChatGPTRequest
import com.coverlettersai.gateway.source.OpenAIDataSource
import com.coverlettersai.boundary.response.ChatGPTResponse

class OpenAIRepository(private val remoteDataSource: OpenAIDataSource) : OpenAIDataSource {
    override suspend fun getChatGPTResponse(chatGPTRequest: ChatGPTRequest): ChatGPTResponse {
        return remoteDataSource.getChatGPTResponse(chatGPTRequest)
    }
}