package com.coverlettersai.boundary.response


data class CreateCoverLetterResponse(
    val linkedinUser: String,
    val companyName: String,
    val coverLetters: List<CoverLetterResponse>,
    val createdAt: String
)
