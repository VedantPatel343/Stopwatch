package com.example.stopwatch.util

import androidx.compose.ui.graphics.Color
import com.example.stopwatch.presentation.theme.NEON

fun getIconColorByCurrentTheme(theme: String) : Color {
    return when(theme) {
        Constants.AppTheme.DAY -> Color.Black
        Constants.AppTheme.NIGHT -> Color.White
        Constants.AppTheme.WATCH -> Color.Black
        Constants.AppTheme.CLASSIC -> Color.Yellow
        Constants.AppTheme.OLD_SCHOOL -> Color.White
        else -> {Color.Black}
    }
}