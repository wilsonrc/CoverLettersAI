package com.coverlettersai.boundary.response

data class OptionsListClientResponse(
    val types : List<String>,
    val options : List<OptionClientResponse>
)
