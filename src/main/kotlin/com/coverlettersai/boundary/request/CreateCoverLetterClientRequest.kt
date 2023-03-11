package com.coverlettersai.boundary.request

data class CreateCoverLetterClientRequest(
    val companyName: String,
    val userName : String,
    val jobPostDescription: String,
    val linkedinUser: String,
    val coverLetterSetting: CoverLetterSetting
)



