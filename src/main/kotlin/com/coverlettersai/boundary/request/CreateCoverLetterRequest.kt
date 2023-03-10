package com.coverlettersai.boundary.request

data class CreateCoverLetterRequest(
    val companyName: String,
    val jobPostDescription: String,
    val linkedinUser: String,
    val coverLetterSize: String,
    val mood: String
)



