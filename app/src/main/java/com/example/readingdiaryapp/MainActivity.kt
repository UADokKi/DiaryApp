package com.example.readingdiaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.readingdiaryapp.ui.theme.ReadingDiaryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReadingDiaryAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    // Main content goes here
                }
            }
        }
    }
}
