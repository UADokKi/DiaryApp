package com.example.readingdiaryapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.readingdiaryapp.DiaryEntry
import com.example.readingdiaryapp.DiaryViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun ViewDiaryEntriesScreen(navController: NavController, diaryViewModel: DiaryViewModel) {
    val diaryEntries by diaryViewModel.diaryEntries.observeAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_entry") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Entry")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(diaryEntries) { entry ->
                DiaryEntryCard(entry = entry, onClick = {
                    navController.navigate("edit_entry/${entry.id}")
                }, onDeleteClick = {
                    diaryViewModel.removeDiaryEntry(entry.id)
                })
            }
        }
    }
}

@Composable
fun DiaryEntryCard(entry: DiaryEntry, onClick: () -> Unit, onDeleteClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
        .clickable { onClick() }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Date: ${entry.date}")
            Text(text = "Book Title: ${entry.bookTitle}")
            Text(text = "Pages Read: ${entry.pagesRead}")
            Text(text = "Child Comments: ${entry.childComments}")
            Text(text = "Teacher Comments: ${entry.teacherComments}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onDeleteClick, colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)) {
                Text(text = "Delete", color = MaterialTheme.colors.onError)
            }
        }
    }
}
