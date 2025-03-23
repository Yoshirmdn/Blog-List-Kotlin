package com.rioramdani0034.assesment1.ui.model

data class Blog(
    val id: Int,
    val title: String,
    val content: String,
    val author: String,
    val category: String,
    val uploadDate: String,
)

val blogList = mutableListOf(
    Blog(1, "Belajar Kotlin", "Kotlin adalah bahasa pemrograman modern...", "Rio Ramdani", "Pemrograman", "2025-12-04"),
)
