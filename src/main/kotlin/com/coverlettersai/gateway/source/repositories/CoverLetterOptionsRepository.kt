package com.coverlettersai.gateway.source.repositories

import com.coverlettersai.boundary.response.OptionClientResponse
import com.coverlettersai.boundary.response.OptionsListClientResponse

class CoverLetterOptionsRepository {

    private val sizeOptions = listOf<OptionClientResponse>(
        OptionClientResponse(
            id = 1,
            name = "Short",
            selected = false,
            type = "size"
        ),
        OptionClientResponse(
            id = 2,
            name = "Medium",
            selected = false,
            type = "size"
        ),
        OptionClientResponse(
            id = 3,
            name = "Large",
            selected = false,
            type = "size"
        )
    )

    private val formalityOptions = listOf<OptionClientResponse>(
        OptionClientResponse(
            id = 1,
            name = "Formal",
            selected = false,
            type = "formality"
        ),
        OptionClientResponse(
            id = 2,
            name = "Neutral",
            selected = false,
            type = "formality"
        ),
        OptionClientResponse(
            id = 3,
            name = "Informal",
            selected = false,
            type = "formality"
        )
    )

    private val intentOptions = listOf<OptionClientResponse>(
        OptionClientResponse(
            id = 1,
            name = "Story telling",
            selected = false,
            type = "intent"
        ),
        OptionClientResponse(
            id = 2,
            name = "Results Oriented",
            selected = false,
            type = "intent"
        ),
        OptionClientResponse(
            id = 3,
            name = "Cultural Fit",
            selected = false,
            type = "intent"
        )
    )

    private val moodOptions = listOf<OptionClientResponse>(
        OptionClientResponse(
            id = 1,
            name = "Happy",
            selected = false,
            type = "mood"
        ),
        OptionClientResponse(
            id = 2,
            name = "Neutral",
            selected = false,
            type = "mood"
        ),
        OptionClientResponse(
            id = 3,
            name = "Excited",
            selected = false,
            type = "mood"
        )
    )

    fun getOptions(): OptionsListClientResponse {
        val allOptions = mutableListOf<OptionClientResponse>()
        allOptions.addAll(sizeOptions)
        allOptions.addAll(formalityOptions)
        allOptions.addAll(intentOptions)
        allOptions.addAll(moodOptions)

        val allTypes = listOf("size", "formality", "intent", "mood")
        return OptionsListClientResponse(types = allTypes, options = allOptions)
    }
}