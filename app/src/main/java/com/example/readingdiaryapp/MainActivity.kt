package com.example.readingdiaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.readingdiaryapp.screens.AddDiaryEntryScreen
import com.example.readingdiaryapp.screens.ViewDiaryEntriesScreen
import com.example.readingdiaryapp.ui.theme.ReadingDiaryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReadingDiaryAppTheme {
                Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
                    DiaryApp()
                }
            }
        }
    }
}

@Composable
fun DiaryApp() {
    val navController = rememberNavController()
    val diaryViewModel: DiaryViewModel = viewModel()

    NavHost(navController = navController, startDestination = "view_entries") {
        composable("view_entries") {
            ViewDiaryEntriesScreen(navController, diaryViewModel)
        }
        composable("add_entry") {
            AddDiaryEntryScreen(navController, diaryViewModel)
        }
        composable("edit_entry/{entryId}") { backStackEntry ->
            val entryId = backStackEntry.arguments?.getString("entryId")?.toIntOrNull()
            entryId?.let {
                AddDiaryEntryScreen(navController, diaryViewModel, entryId)
            }
        }
    }
}
