package com.example.stopwatch.util

import androidx.compose.ui.graphics.Color
import com.example.stopwatch.presentation.theme.NEON

fun getCurrentThemeColor(theme: String) : Color {
    return when(theme) {
        Constants.AppTheme.DAY -> Color.White
        Constants.AppTheme.NIGHT -> Color.Black
        Constants.AppTheme.WATCH -> Color.Black
        Constants.AppTheme.CLASSIC -> Color.Black
        Constants.AppTheme.OLD_SCHOOL -> Color.Black
        else -> {Color.Black}
    }
}