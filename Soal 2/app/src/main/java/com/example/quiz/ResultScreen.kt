package com.example.quiz.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quiz.model.QuizAnswer

@Composable
fun ResultScreen(
    score: Int,
    total: Int,
    wrongAnswers: List<QuizAnswer>,
    onRestart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quiz Finished!", style = MaterialTheme.typography.headlineSmall)
        Text("Your Score: $score / $total", style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(16.dp))

        if (wrongAnswers.isEmpty()) {
            Text(
                text = "Perfect! No wrong answers.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f, fill = false),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(wrongAnswers, key = { it.questionNumber }) { answer ->
                    WrongAnswerItem(answer)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onRestart,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Restart Quiz")
        }
    }
}
