package com.example.readingdiaryapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DiaryViewModel : ViewModel() {
    private val _diaryEntries = MutableLiveData<List<DiaryEntry>>()
    val diaryEntries: LiveData<List<DiaryEntry>> get() = _diaryEntries

    private var allDiaryEntries = listOf(
        DiaryEntry(1, "2024-07-01", "Book A", "1-10", "Loved it!", "Great progress!"),
        DiaryEntry(2, "2024-07-02", "Book B", "11-20", "It was okay.", "Needs improvement.")
    )

    init {
        _diaryEntries.value = allDiaryEntries
    }

    fun addDiaryEntry(entry: DiaryEntry) {
        allDiaryEntries = allDiaryEntries + entry
        _diaryEntries.value = allDiaryEntries
    }

    fun editDiaryEntry(updatedEntry: DiaryEntry) {
        allDiaryEntries = allDiaryEntries.map { if (it.id == updatedEntry.id) updatedEntry else it }
        _diaryEntries.value = allDiaryEntries
    }

    fun removeDiaryEntry(entryId: Int) {
        allDiaryEntries = allDiaryEntries.filter { it.id != entryId }
        _diaryEntries.value = allDiaryEntries
    }

    fun getDiaryEntry(entryId: Int): DiaryEntry? {
        return allDiaryEntries.find { it.id == entryId }
    }

    fun searchDiaryEntries(query: String) {
        if (query.isEmpty()) {
            _diaryEntries.value = allDiaryEntries
        } else {
            _diaryEntries.value = allDiaryEntries.filter {
                it.bookTitle.contains(query, ignoreCase = true) ||
                        it.childComments.contains(query, ignoreCase = true) ||
                        it.teacherComments.contains(query, ignoreCase = true)
            }
        }
    }
}
