package com.example.quiz.model

data class QuizAnswer(
    val questionNumber: Int,
    val questionText: String,
    val selectedAnswer: String,
    val correctAnswer: String
) {
    val isCorrect: Boolean get() = selectedAnswer == correctAnswer
}
