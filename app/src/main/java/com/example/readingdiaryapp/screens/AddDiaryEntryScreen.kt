package com.example.readingdiaryapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.readingdiaryapp.DiaryEntry
import com.example.readingdiaryapp.DiaryViewModel
import java.util.*

@Composable
fun AddDiaryEntryScreen(navController: NavController, diaryViewModel: DiaryViewModel, entryId: Int? = null) {
    var date by remember { mutableStateOf("") }
    var bookTitle by remember { mutableStateOf("") }
    var pagesRead by remember { mutableStateOf("") }
    var childComments by remember { mutableStateOf("") }
    var teacherComments by remember { mutableStateOf("") }

    // If editing an entry, initialize the fields with existing data
    val entry = entryId?.let { diaryViewModel.getDiaryEntry(it) }
    LaunchedEffect(entry) {
        entry?.let {
            date = it.date
            bookTitle = it.bookTitle
            pagesRead = it.pagesRead
            childComments = it.childComments
            teacherComments = it.teacherComments
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date / Time Stamp") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = bookTitle,
            onValueChange = { bookTitle = it },
            label = { Text("Book Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = pagesRead,
            onValueChange = { pagesRead = it },
            label = { Text("Pages Read") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = childComments,
            onValueChange = { childComments = it },
            label = { Text("Child Comments / Enjoyment Rating") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = teacherComments,
            onValueChange = { teacherComments = it },
            label = { Text("Teacher / Parent Comments") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val newEntry = DiaryEntry(
                    id = entry?.id ?: UUID.randomUUID().hashCode(),
                    date = date,
                    bookTitle = bookTitle,
                    pagesRead = pagesRead,
                    childComments = childComments,
                    teacherComments = teacherComments
                )
                if (entry != null) {
                    diaryViewModel.editDiaryEntry(newEntry)
                } else {
                    diaryViewModel.addDiaryEntry(newEntry)
                }
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Entry")
        }
    }
}
