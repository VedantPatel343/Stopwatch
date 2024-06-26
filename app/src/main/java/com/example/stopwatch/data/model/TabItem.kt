package com.example.stopwatch.data.model

import com.example.stopwatch.R

data class TabItem(
    val title: String,
    val selectedIcon: Int,
    val unSelectedIcon: Int
) {
    companion object {
        val Stopwatch = TabItem(
            title = "Stopwatch",
            selectedIcon = R.drawable.ic_stopwatch_filled,
            unSelectedIcon = R.drawable.ic_stopwatch_outlined
        )
        val Timer = TabItem(
            title = "Timer",
            selectedIcon = R.drawable.ic_timer_filled,
            unSelectedIcon = R.drawable.ic_timer_outlined
        )
    }
}
