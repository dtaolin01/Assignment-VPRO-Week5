package com.example.quiz.model

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctAnswer: String
)
