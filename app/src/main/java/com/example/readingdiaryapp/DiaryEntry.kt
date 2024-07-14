package com.example.readingdiaryapp

data class DiaryEntry(
    val id: Int,
    val date: String,
    val bookTitle: String,
    val pagesRead: String,
    val childComments: String,
    val teacherComments: String
)
