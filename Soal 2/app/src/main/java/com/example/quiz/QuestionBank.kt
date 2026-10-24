package com.example.quiz.model

object QuestionBank {
    val questions: List<Question> = listOf(
        Question(
            id = 1,
            text = "Berapa jumlah roda pada mobil penumpang pada umumnya?",
            options = listOf("2", "3", "4", "6"),
            correctAnswer = "4"
        ),
        Question(
            id = 2,
            text = "Cairan apa yang digunakan untuk mendinginkan mesin mobil?",
            options = listOf("Radiator coolant", "Minyak rem", "Oli mesin", "Air aki"),
            correctAnswer = "Radiator coolant"
        ),
        Question(
            id = 3,
            text = "Komponen mana yang berfungsi memperlambat dan menghentikan mobil?",
            options = listOf("Kopling", "Rem", "Busi", "Knalpot"),
            correctAnswer = "Rem"
        ),
        Question(
            id = 4,
            text = "Mobil dari Jepang berikut ini adalah?",
            options = listOf("Toyota", "BMW", "Ford", "Ferrari"),
            correctAnswer = "Toyota"
        ),
        Question(
            id = 5,
            text = "Apa fungsi utama aki pada mobil?",
            options = listOf(
                "Menyimpan bahan bakar",
                "Menyuplai listrik untuk menyalakan mesin",
                "Mendinginkan mesin",
                "Mengatur kecepatan"
            ),
            correctAnswer = "Menyuplai listrik untuk menyalakan mesin"
        )
    )
}