package com.example.expenses.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.expenses.model.Expense

class ExpenseViewModel : ViewModel() {

    private val _expenses = mutableStateListOf<Expense>()
    val expenses: List<Expense> get() = _expenses

    // Total otomatis ikut berubah setiap list berubah (tambah / edit / hapus)
    val totalExpense: Long by derivedStateOf { _expenses.sumOf { it.amount } }

    // ---- State form tambah ----
    var nameInput by mutableStateOf("")
        private set
    var amountInput by mutableStateOf("")
        private set
    var nameError by mutableStateOf<String?>(null)
        private set
    var amountError by mutableStateOf<String?>(null)
        private set

    // ---- State edit (bonus) ----
    var editingExpense by mutableStateOf<Expense?>(null)
        private set
    var editAmountInput by mutableStateOf("")
        private set
    var editError by mutableStateOf<String?>(null)
        private set

    private var nextId = 1L

    fun onNameChange(value: String) {
        nameInput = value
        nameError = null
    }

    fun onAmountChange(value: String) {
        // hanya terima angka
        amountInput = value.filter { it.isDigit() }.take(15)
        amountError = null
    }

    fun addExpense() {
        val name = nameInput.trim()
        val amount = amountInput.toLongOrNull()

        var valid = true
        if (name.isEmpty()) {
            nameError = "Name must not be empty"
            valid = false
        }
        if (amount == null || amount <= 0L) {
            amountError = "Amount must be greater than 0"
            valid = false
        }
        if (!valid || amount == null) return

        _expenses.add(Expense(id = nextId++, name = name, amount = amount))
        nameInput = ""
        amountInput = ""
    }

    fun deleteExpense(expense: Expense) {
        _expenses.removeAll { it.id == expense.id }
    }

    // ---- Edit ----
    fun startEdit(expense: Expense) {
        editingExpense = expense
        editAmountInput = expense.amount.toString()
        editError = null
    }

    fun onEditAmountChange(value: String) {
        editAmountInput = value.filter { it.isDigit() }.take(15)
        editError = null
    }

    fun saveEdit() {
        val target = editingExpense ?: return
        val newAmount = editAmountInput.toLongOrNull()
        if (newAmount == null || newAmount <= 0L) {
            editError = "Amount must be greater than 0"
            return
        }
        val index = _expenses.indexOfFirst { it.id == target.id }
        if (index != -1) {
            _expenses[index] = _expenses[index].copy(amount = newAmount)
        }
        cancelEdit()
    }

    fun cancelEdit() {
        editingExpense = null
        editAmountInput = ""
        editError = null
    }
}
