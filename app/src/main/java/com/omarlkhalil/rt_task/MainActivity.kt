package com.omarlkhalil.rt_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.omarlkhalil.rt_task.presentation.screens.MainScreen
import com.omarlkhalil.rt_task.presentation.theme.RTScreenTheme
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

