package com.example.quiz.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.quiz.model.Question
import com.example.quiz.model.QuestionBank
import com.example.quiz.model.QuizAnswer

class QuizViewModel : ViewModel() {

    private val allQuestions: List<Question> = QuestionBank.questions
    val maxQuestions: Int = allQuestions.size

    var stage by mutableStateOf(QuizStage.START)
        private set

    // ---- Halaman start (bonus) ----
    var countInput by mutableStateOf("3")
        private set

    val countError: String?
        get() {
            if (countInput.isEmpty()) return null
            val n = countInput.toIntOrNull()
            return when {
                n == null || n < 1 -> "Minimum is 1 question"
                n > maxQuestions -> "Maximum is $maxQuestions questions"
                else -> null
            }
        }

    val canStart: Boolean
        get() = countInput.isNotEmpty() && countInput.toIntOrNull() != null && countError == null

    // ---- State quiz ----
    private var activeQuestions by mutableStateOf<List<Question>>(emptyList())
    var currentIndex by mutableIntStateOf(0)
        private set
    var selectedAnswer by mutableStateOf<String?>(null)
        private set

    private val _answers = mutableStateListOf<QuizAnswer>()
    val answers: List<QuizAnswer> get() = _answers

    val totalQuestions: Int get() = activeQuestions.size
    val currentQuestion: Question? get() = activeQuestions.getOrNull(currentIndex)
    val score: Int get() = _answers.count { it.isCorrect }
    val wrongAnswers: List<QuizAnswer> get() = _answers.filter { !it.isCorrect }

    fun onCountChange(value: String) {
        countInput = value.filter { it.isDigit() }.take(3)
    }

    fun startQuiz() {
        val n = countInput.toIntOrNull() ?: return
        if (n < 1 || n > maxQuestions) return
        beginQuiz(n)
    }

    fun selectAnswer(option: String) {
        selectedAnswer = option
    }

    fun nextQuestion() {
        val question = currentQuestion ?: return
        val selected = selectedAnswer ?: return

        _answers.add(
            QuizAnswer(
                questionNumber = currentIndex + 1,
                questionText = question.text,
                selectedAnswer = selected,
                correctAnswer = question.correctAnswer
            )
        )

        if (currentIndex + 1 >= activeQuestions.size) {
            stage = QuizStage.RESULT
        } else {
            currentIndex++
            selectedAnswer = null
        }
    }

    // Mulai ulang dengan jumlah soal yang sama, urutan/soal diacak ulang
    fun restartQuiz() {
        beginQuiz(activeQuestions.size)
    }

    private fun beginQuiz(count: Int) {
        activeQuestions = allQuestions.shuffled().take(count)
        _answers.clear()
        currentIndex = 0
        selectedAnswer = null
        stage = QuizStage.QUIZ
    }
}
