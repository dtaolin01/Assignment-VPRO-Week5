package com.example.expenses.util

import java.text.NumberFormat
import java.util.Locale

fun formatRupiah(amount: Long): String {
    val formatter = NumberFormat.getInstance(Locale("in", "ID"))
    return "Rp ${formatter.format(amount)}"
}
