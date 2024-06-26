package com.example.stopwatch.presentation.stopwatch

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stopwatch.data.StopwatchState
import com.example.stopwatch.data.model.Lap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StopwatchViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(StopwatchUiState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), StopwatchUiState())

    private var startTime = 0L
    private var elapsedTime = 0L
    private var lastLapTime = "00:00:000"
    val handler = Handler(Looper.getMainLooper())

    fun onEvent(event: StopwatchEvent) {
        when (event) {
            StopwatchEvent.StartStopwatch -> startStopwatch()
            StopwatchEvent.PauseStopwatch -> pauseStopwatch()
            StopwatchEvent.ResetStopwatch -> resetStopwatch()
            StopwatchEvent.AddLap -> addLap()
        }
    }

    private fun startStopwatch() {
        startTime = System.currentTimeMillis()
        _state.update {
            it.copy(stopwatchState = StopwatchState.Running)
        }
        handler.post(runnable)
    }

    private fun pauseStopwatch() {
        handler.removeCallbacks(runnable)
        elapsedTime += System.currentTimeMillis() - startTime
        _state.update {
            it.copy(
                stopwatchState = StopwatchState.Paused
            )
        }
    }

    private fun resetStopwatch() {
        _state.update {
            it.copy(
                stopwatchState = StopwatchState.Ready,
                milliSeconds = "000",
                seconds = "00",
                minutes = "00",
                lapList = emptyList()
            )
        }
    }

    private fun addLap() {

    }

    private val runnable = object : Runnable {
        override fun run() {
            getUpdatedTime { milliseconds, seconds, minutes ->
                _state.update {
                    it.copy(
                        milliSeconds = milliseconds,
                        seconds = seconds,
                        minutes = minutes
                    )
                }
            }
            handler.postDelayed(this, 10)
        }
    }

    fun getUpdatedTime(updateTime: (String, String, String) -> Unit) {
        val totalMilliseconds = if (_state.value.stopwatchState == StopwatchState.Running) {
            elapsedTime + System.currentTimeMillis() - startTime
        } else {
            elapsedTime
        }

        val minutes = (totalMilliseconds / 1000 / 60).toInt().toString().padStart(2, '0')
        val seconds = (totalMilliseconds / 1000 % 60).toInt().toString().padStart(2, '0')
        val milliseconds = (totalMilliseconds % 1000).toInt().toString().padStart(3, '0')
        updateTime(milliseconds, seconds, minutes)
    }

    data class StopwatchUiState(
        val stopwatchState: StopwatchState = StopwatchState.Ready,
        val milliSeconds: String = "000",
        val seconds: String = "00",
        val minutes: String = "00",
        val lapList: List<Lap> = emptyList()
    )
}