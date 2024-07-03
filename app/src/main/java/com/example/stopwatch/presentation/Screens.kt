package com.example.stopwatch.presentation

import com.example.stopwatch.R

sealed class Screens (
    val route: String,
    val title: String,
    val filledIcon: Int,
    val outlinedIcon: Int
) {
    data object Stopwatch : Screens(
        title = "Stopwatch",
        route = "stopwatch_screen",
        filledIcon = R.drawable.ic_stopwatch_filled,
        outlinedIcon = R.drawable.ic_stopwatch_outlined
    )
    data object Timer : Screens(
        title = "Timer",
        route = "timer_screen",
        filledIcon = R.drawable.ic_timer_filled,
        outlinedIcon = R.drawable.ic_timer_outlined
    )
}
