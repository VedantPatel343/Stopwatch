package com.example.stopwatch.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize

@Composable
fun CircularIndicators(
    modifier: Modifier = Modifier,
    secondIndicatorSize: DpSize,
    minuteIndicatorSize: DpSize,
    hourIndicatorSize: DpSize,
    seconds: Int,
    minutes: Int,
    hours: Int,
    alwaysShowHours: Boolean
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularIndicator(
            canvasSize = secondIndicatorSize,
            currentIndicatorValue = seconds,
            maxIndicatorValue = 60,
            animationDuration = 500,
            backgroundIndicatorStrokeWidth = 30f,
            foregroundIndicatorStrokeWidth = 30f
        )

        CircularIndicator(
            canvasSize = minuteIndicatorSize,
            currentIndicatorValue = minutes,
            maxIndicatorValue = 60,
            animationDuration = 1000,
            backgroundIndicatorStrokeWidth = 30f,
            foregroundIndicatorStrokeWidth = 30f
        )

        if (hours > 0 || alwaysShowHours) {
            CircularIndicator(
                canvasSize = hourIndicatorSize,
                currentIndicatorValue = hours,
                maxIndicatorValue = 60,
                animationDuration = 2000,
                backgroundIndicatorStrokeWidth = 30f,
                foregroundIndicatorStrokeWidth = 30f
            )
        }
    }
}