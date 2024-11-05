package com.omarlkhalil.examate.domain.model.questions



data class OralQuestionsModel(
    val question: String = "",
    val answersCount: Int = 0,
    val answer: String = "",
    val titles: List<String> = mutableListOf(),
    val date: String = ""
)