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
        val jobDescription = "At INGENIOUS.BUILD, we’ve cracked the code and built a world-class platform for companies of all sizes within the real estate and construction industries across the globe, enabling them to simply and effortlessly manage all aspects of their projects--from inception to completion, and both internally and in conjunction with the other project participants.\n" +
                "\n" +
                "Project managers drool. Data geeks giggle. Execs actually sleep well. And the rest simply stare slack-jawed at their screens unable to comprehend how in the world we pulled this off...\n" +
                "\n" +
                "Think about what you could do here… At INGENIOUS.BUILD, you’ll be participating in one of the most exciting and unparalleled journeys of your career--a journey to pioneer the first cloud application to manage the entire lifecycle of a real estate construction project. In short, you’ll be building what some have claimed is the holy grail of construction project management.\n" +
                "\n" +
                "It’s big. Bold. Audacious, even. But that's how we roll...\n" +
                "\n" +
                "And if you’ve got what it takes, you’ll be directly involved in its development. The rest, as they say, will be history...\n" +
                "\n" +
                "So... think you've got the right stuff?\n" +
                "\n" +
                "We are looking for a talented Android Engineer to join our remote team. If you have top-notch programming skills and a deep-rooted passion for developing applications or improving existing ones--plus you like to solve problems--we would like to meet you. As an Android Engineer you will work closely within a small team in SCRUM (but in conjunction with other dev teams) to ensure system consistency and to improve the user experience.\n" +
                "\n" +
                "What is vital, however, is that you have, and demonstrate, a burning desire to constantly learn new things and to be proactive in your development efforts.\n" +
                "\n" +
                "Responsibilities\n" +
                "\n" +
                "Collaborate with your team to plan, build, and deliver world-class software.\n" +
                "Refactor, optimize, and improve the existing codebase.\n" +
                "Use your voice to help shape your own personal growth, your team, the department, and the company.\n" +
                "Required Qualifications\n" +
                "\n" +
                "Kotlin.\n" +
                "Jetpack Compose.\n" +
                "Experience building complex modular Android App Architecture.\n" +
                "Retrofit.\n" +
                "Coroutines.\n" +
                "Experience launching apps in Play Store.\n" +
                "REST.\n" +
                "Teamwork skills with a problem-solving attitude.\n" +
                "Communicative English (reading / writing). Minimum level B2.\n" +
                "Analytical skills.\n" +
                "Preferred Qualifications\n" +
                "\n" +
                "Experience with building large scale SaaS apps like CRM, CMS, and ERP.\n" +
                "Unit tests.\n" +
                "Web sockets.\n" +
                "SCRUM.\n" +
                "Jira.\n" +
                "Figma.\n" +
                "MVVM."

        val sizeText = when (companySetting.size.lowercase()) {
            "short" -> "Generate a Short and concise cover letter"
            "medium" -> "Generate a medium size cover letter"
            "large" -> "Generate a long cover letter"
            else -> "Generate a medium size and concise cover Letter"
        }

        val formalityText = when (companySetting.formality.lowercase()) {
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