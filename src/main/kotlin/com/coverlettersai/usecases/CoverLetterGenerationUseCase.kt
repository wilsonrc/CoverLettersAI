package com.coverlettersai.usecases

import com.coverlettersai.boundary.request.ChatGPTRequest
import com.coverlettersai.boundary.request.CreateCoverLetterClientRequest
import com.coverlettersai.gateway.source.OpenAIDataSource
import com.coverlettersai.boundary.request.MessageRequest
import com.coverlettersai.boundary.response.ChatGPTResponse

class CoverLetterGenerationUseCase(private val openAIRepository: OpenAIDataSource) {
    suspend fun generateCoverLetter(createCoverLetterRequest: CreateCoverLetterClientRequest): ChatGPTResponse {
        //TODO GET PROFILE SUMMARY FROM LINKEDIN
        val profileSummary =
            "I'm a software engineer specializing in Android development with more than seven years of professional experience. Through that time, I have had the opportunity to work as an independent consultant solo-building apps from requirements to product delivery, but also as part of large teams for mobile apps from thousands to millions of users, including video streaming, e-commerce, online fitness, time tracking, casino, and proprietary hardware apps.\n" +
                    "\n" +
                    "As a professional, I enjoy working on challenging projects, and I consider myself a knowledge lover; I always learn new things, technologies, and best practices. People know me as someone adaptable and entirely passionate about continuous self-improvement.\n" +
                    "\n" +
                    "Technologies: Android, Kotlin, Java, Android Jetpack, SQLite, RxJava, Firebase Crashlytics, Firebase, and more."

        val messageList = listOf(
            MessageRequest(
                "system",
                "This is process in which you will use a company name and job description to generate a cover letter."
            ),
            MessageRequest(
                "system",
                " You will use the profile summary of the user to generate the cover letter matching their skills with the job description.USE ONLY INFORMATION IN THE PROFILE SUMMARY"
            ),
            MessageRequest(
                "system",
                "Create a cover letter for ${createCoverLetterRequest.companyName} for the position of ${createCoverLetterRequest.jobPostDescription}"
            ),
            MessageRequest(
                "user",
                "This is the profile summary of the user $profileSummary"
            )
        )

        return openAIRepository.getChatGPTResponse(ChatGPTRequest("gpt-3.5-turbo", messageList))
    }
}