package com.coverlettersai.boundary.response


data class CreateCoverLetterClientResponse(
    val linkedinUser: String,
    val companyName: String,
    val coverLetters: List<CoverLetterClientResponse>,
    val createdAt: String
)
