package com.example.stopwatch.presentation.stopwatch

sealed class StopwatchEvent {

    data object StartResumeStopwatch : StopwatchEvent()

    data object PauseStopwatch : StopwatchEvent()

    data object ResetStopwatch : StopwatchEvent()

    data object AddLap : StopwatchEvent()

    data class UpdateTheme(val theme: String) : StopwatchEvent()

}