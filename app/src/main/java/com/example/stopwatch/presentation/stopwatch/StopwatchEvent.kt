package com.example.stopwatch.presentation.stopwatch

sealed class StopwatchEvent {

    data object StartStopwatch : StopwatchEvent()

    data object PauseStopwatch : StopwatchEvent()

    data object ResetStopwatch : StopwatchEvent()

    data object AddLap : StopwatchEvent()

}