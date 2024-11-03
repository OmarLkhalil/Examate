package com.omarlkhalil.examate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.omarlkhalil.examate.presentation.screens.MainScreen
import com.omarlkhalil.examate.presentation.theme.RTScreenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RTScreenTheme {
                MainScreen()
            }
        }
    }
}

