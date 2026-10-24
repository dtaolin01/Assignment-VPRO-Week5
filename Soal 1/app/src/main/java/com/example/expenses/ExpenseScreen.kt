package com.example.expenses.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expenses.util.formatRupiah
import com.example.expenses.viewmodel.ExpenseViewModel

@Composable
fun ExpenseScreen(
    modifier: Modifier = Modifier,
    viewModel: ExpenseViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Expenses",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Total Expense : ${formatRupiah(viewModel.totalExpense)}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.nameInput,
            onValueChange = viewModel::onNameChange,
            label = { Text("Expense Name") },
            isError = viewModel.nameError != null,
            supportingText = { viewModel.nameError?.let { Text(it) } },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = viewModel.amountInput,
                onValueChange = viewModel::onAmountChange,
                label = { Text("Amount of Expense") },
                isError = viewModel.amountError != null,
                supportingText = { viewModel.amountError?.let { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = viewModel::addExpense,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add expense")
            }
        }

        Spacer(Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(viewModel.expenses, key = { it.id }) { expense ->
                ExpenseItem(
                    expense = expense,
                    onEdit = { viewModel.startEdit(expense) },
                    onDelete = { viewModel.deleteExpense(expense) }
                )
            }
        }
    }

    viewModel.editingExpense?.let { editing ->
        EditAmountDialog(
            expenseName = editing.name,
            amount = viewModel.editAmountInput,
            error = viewModel.editError,
            onAmountChange = viewModel::onEditAmountChange,
            onSave = viewModel::saveEdit,
            onDismiss = viewModel::cancelEdit
        )
    }
}
