package com.coverlettersai.boundary.request

data class ChatGPTRequest(
    val model : String,
    val messages : List<MessageRequest>,
)
