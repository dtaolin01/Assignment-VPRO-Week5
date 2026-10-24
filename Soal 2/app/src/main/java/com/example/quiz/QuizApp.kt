package com.example.quiz.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quiz.viewmodel.QuizStage
import com.example.quiz.viewmodel.QuizViewModel

@Composable
fun QuizApp(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = viewModel()
) {
    when (viewModel.stage) {
        QuizStage.START -> StartScreen(
            count = viewModel.countInput,
            error = viewModel.countError,
            maxQuestions = viewModel.maxQuestions,
            canStart = viewModel.canStart,
            onCountChange = viewModel::onCountChange,
            onStart = viewModel::startQuiz,
            modifier = modifier
        )

        QuizStage.QUIZ -> viewModel.currentQuestion?.let { question ->
            QuestionScreen(
                questionNumber = viewModel.currentIndex + 1,
                question = question,
                selectedAnswer = viewModel.selectedAnswer,
                onSelect = viewModel::selectAnswer,
                onNext = viewModel::nextQuestion,
                modifier = modifier
            )
        }

        QuizStage.RESULT -> ResultScreen(
            score = viewModel.score,
            total = viewModel.totalQuestions,
            wrongAnswers = viewModel.wrongAnswers,
            onRestart = viewModel::restartQuiz,
            modifier = modifier
        )
    }
}
