package com.coverlettersai.usecases

import com.coverlettersai.boundary.request.ChatGPTRequest
import com.coverlettersai.boundary.request.CreateCoverLetterClientRequest
import com.coverlettersai.gateway.source.OpenAIDataSource
import com.coverlettersai.boundary.request.MessageRequest
import com.coverlettersai.boundary.response.ChatGPTResponse

class CoverLetterGenerationUseCase(private val openAIRepository: OpenAIDataSource) {
    suspend fun generateCoverLetter(createCoverLetterRequest: CreateCoverLetterClientRequest): ChatGPTResponse {
        val companySetting = createCoverLetterRequest.coverLetterSetting
        val linkedinUserSummary = LinkedinUserSummaryUseCase().getLinkedinUserSummary()
        val jobDescription =
            "\'\"We\\\'re looking to hire a Principal Software Developer specialized in Android to join our team. You\\\'ll work with our incredible clients in one of two ways:\\\\n\'+\n\'• Team Augmentation: You will integrate yourself directly into our client\\\'s team and work alongside their existing designers and engineers on a daily basis.\\\\n\'+\n\'• Design & Build: You will work on a FullStack Labs product team to build and deliver a product to our clients.\\\\n\'+\n\'What you\\\'ll get to do:\\\\n\'+\n\'• Iteratively design, implement and deliver software features for our new and existing products.\\\\n\'+\n\'• Develop secure, testable, efficient, and well-documented code.\\\\n\'+\n\'• Build and maintain new tooling (test coverage, build tasks, build automation).\\\\n\'+\n\'• Contribute to the development and maintenance of our Android SDK and mobile app products.\\\\n\'+\n\'• Refactor existing code to higher quality standards using modern best practices.\\\\n\'+\n\'• Collaborate with other developers by participating in pair programming and code reviews.\\\\n\'+\n\'• Work cross-functionally to help drive the company’s technological development.\\\\n\'+\n\'• Collaborate with other team members on designing and building new features and products.\\\\n\'+\n\'• Lead a team working on Android native applications by helping them identify solutions to business needs.\\\\n\'+\n\'What we are looking for:\\\\n\'+\n\'• 8+ years of professional software development experience.\\\\n\'+\n\'• Advanced English is required.\\\\n\'+\n\'• A four-year college degree is required.\\\\n\'+\n\'• Meaningful experience working with Android, Android Studio, Android SDK, and Java (Kotlin).\\\\n\'+\n\'• Able to work effectively in a distributed team environment (remote).\\\\n\'+\n\'• Experience publishing and maintaining apps in the Android app store.\\\\n\'+\n\'• Strong understanding of application architecture, design patterns, and best practices.\\\\n\'+\n\'• Self-directed and comfortable supporting the needs of multiple teams, systems, and products.\\\\n\'+\n\'• Passion for testing and test-driven development.\\\\n\'+\n\'• Commitment to continually improving and expanding your technical skills.\\\\n\'+\n\'\"\'"

        val sizeText = when (companySetting.size.lowercase()) {
            "short" -> "Generate a Short and concise cover letter"
            "medium" -> "Generate a medium size cover letter"
            "large" -> "Generate a long cover letter"
            else -> "Generate a medium size and concise cover Letter"
        }

        val formalityText = when (companySetting.formality.lowercase()) {
            "very_formal" -> "Use a very formal wording style for the cover letter"
            "formal" -> "Use a formal wording style for the cover letter"
            "neutral" -> "Use a neutral wording style for the cover letter"
            "informal" -> "Use an informal wording style for the cover letter"
            else -> "Use a formal wording style for the cover letter"
        }

        val intentText = when (companySetting.intent.lowercase()) {
            "story_telling" -> "Give me a story telling version of a cover letter"
            "results_oriented" -> "Give me a results oriented version of a style"
            "cultural_fit_oriented" -> "Give me a cultural fit oriented version of the cover letter"
            else -> "Give me a well done cover letter"
        }

        val moodText = when (companySetting.mood.lowercase()) {
            "happy" -> "Use a happy mood style wording"
            "neutral" -> "Use a happy style wording"
            "excited" -> "Use a excited style wording"
            else -> "Generate a neutral cover letter"
        }

        //TODO GET PROFILE SUMMARY FROM LINKEDIN
        val messageList = listOf(
            MessageRequest(
                "system",
                "This is process in which you will use a company name and job description to generate a cover letter."
            ),
            MessageRequest(
                "system",
                "You will use the profile summary of the user to generate the cover letter matching their skills with the job description.USE ONLY INFORMATION IN THE PROFILE SUMMARY"
            ),
            MessageRequest(
                "system",
                "Create a cover letter for ${createCoverLetterRequest.companyName} for the position of ${jobDescription} in the name of ${createCoverLetterRequest.userName}"
            ),
            MessageRequest(
                "user",
                "FOLLOW THE NEXT SET OF INSTRUCTIONS CAREFULLY: $intentText. $formalityText. $moodText. "
            ),
            MessageRequest(
                "user",
                sizeText
            ),
            MessageRequest(
                "user",
                "This is the profile summary of the user $linkedinUserSummary follow the instructions to generate a cover letter:"
            )
        )

        return openAIRepository.getChatGPTResponse(ChatGPTRequest("gpt-3.5-turbo", messageList))
    }
}