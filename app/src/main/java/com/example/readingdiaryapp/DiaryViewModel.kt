package com.example.readingdiaryapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class DiaryViewModel : ViewModel() {
    val diaryEntries = MutableLiveData<List<DiaryEntry>>()

    init {
        diaryEntries.value = mutableListOf()
    }

    fun addDiaryEntry(entry: DiaryEntry) {
        val updatedEntries = diaryEntries.value?.toMutableList()
        updatedEntries?.add(entry)
        diaryEntries.value = updatedEntries
    }

    // Additional functions for editing and removing entries
}
