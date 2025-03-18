package com.rioramdani0034.assesment1.ui.model

data class Blog(
    val id: Int,
    val title: String,
    val content: String,
    val author: String,
    val category: String,
    val uploadDate: String,
)

val blogList = listOf(
    Blog(1, "Belajar Kotlin", "Kotlin adalah bahasa pemrograman modern...", "Rio Ramdani", "Pemrograman", "18 Maret 2025"),
    Blog(2, "Jetpack Compose", "Jetpack Compose mempermudah pembuatan UI...", "Dani Wijaya", "Android", "19 Maret 2025"),
    Blog(3, "Pemrograman Android", "Membahas konsep Activity dan Fragment...", "Andi Saputra", "Android", "12 Maret 2025"),
    Blog(4, "UI/UX Design", "Membahas dasar desain antarmuka pengguna...", "Siti Aisyah", "Design", "8 Maret 2025")
)
