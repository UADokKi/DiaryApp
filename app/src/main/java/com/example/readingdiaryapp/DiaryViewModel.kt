package com.example.readingdiaryapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class DiaryViewModel : ViewModel() {
    val diaryEntries = MutableLiveData<List<DiaryEntry>>()

    init {
        diaryEntries.value = mutableListOf(
            DiaryEntry(1, "2024-07-01", "Book A", "1-10", "Loved it!", "Great progress!"),
            DiaryEntry(2, "2024-07-02", "Book B", "11-20", "It was okay.", "Needs improvement.")
        )
    }

    fun addDiaryEntry(entry: DiaryEntry) {
        val updatedEntries = diaryEntries.value?.toMutableList() ?: mutableListOf()
        updatedEntries.add(entry)
        diaryEntries.value = updatedEntries
    }

    fun editDiaryEntry(updatedEntry: DiaryEntry) {
        val updatedEntries = diaryEntries.value?.toMutableList() ?: mutableListOf()
        val index = updatedEntries.indexOfFirst { it.id == updatedEntry.id }
        if (index != -1) {
            updatedEntries[index] = updatedEntry
            diaryEntries.value = updatedEntries
        }
    }

    fun removeDiaryEntry(entryId: Int) {
        val updatedEntries = diaryEntries.value?.toMutableList() ?: mutableListOf()
        updatedEntries.removeAll { it.id == entryId }
        diaryEntries.value = updatedEntries
    }

    fun getDiaryEntry(entryId: Int): DiaryEntry? {
        return diaryEntries.value?.find { it.id == entryId }
    }
}
