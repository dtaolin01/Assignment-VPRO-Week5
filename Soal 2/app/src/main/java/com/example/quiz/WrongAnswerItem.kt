package com.example.quiz.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quiz.model.QuizAnswer

@Composable
fun WrongAnswerItem(
    answer: QuizAnswer,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Q${answer.questionNumber}: ${answer.questionText}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Your Answer: ${answer.selectedAnswer}",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFD32F2F)
        )
        Text(
            text = "Correct Answer: ${answer.correctAnswer}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
    }
}
