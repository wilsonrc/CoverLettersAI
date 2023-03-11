package com.coverlettersai.gateway.source

import com.coverlettersai.boundary.request.ChatGPTRequest
import com.coverlettersai.boundary.response.ChatGPTResponse
interface OpenAIDataSource {
   suspend fun getChatGPTResponse(
        chatGPTRequest : ChatGPTRequest
    ) : ChatGPTResponse
}