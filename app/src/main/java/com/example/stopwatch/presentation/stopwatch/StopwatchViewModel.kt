package com.example.stopwatch.presentation.stopwatch

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stopwatch.data.StopwatchState
import com.example.stopwatch.data.model.Lap
import com.example.stopwatch.data.repo.DataStoreRepo
import com.example.stopwatch.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StopwatchViewModel @Inject constructor(
    private val dataStoreRepo: DataStoreRepo
) : ViewModel() {

    private val _state = MutableStateFlow(StopwatchUiState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), StopwatchUiState())

    private val _theme = MutableStateFlow(Constants.AppTheme.CLASSIC)
    val theme =
        _theme.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), Constants.AppTheme.CLASSIC)

    init {
        viewModelScope.launch {
            getUpdatedTheme()
        }
    }

    private var startTime = 0L
    private var elapsedTime = 0L
    private var lastLapTime = "00:00:00:000"
    val handler = Handler(Looper.getMainLooper())

    fun onEvent(event: StopwatchEvent) {
        when (event) {
            StopwatchEvent.StartResumeStopwatch -> startResumeStopwatch()
            StopwatchEvent.PauseStopwatch -> pauseStopwatch()
            StopwatchEvent.ResetStopwatch -> resetStopwatch()
            StopwatchEvent.AddLap -> addLap()
            is StopwatchEvent.UpdateTheme -> updateTheme(event.theme)
        }
    }

    private fun startResumeStopwatch() {
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

    private fun updateTheme(theme: String) {
        viewModelScope.launch {
            dataStoreRepo.updateTheme(theme)
            getUpdatedTheme()
        }
    }

    private suspend fun getUpdatedTheme() {
        dataStoreRepo.getTheme().collectLatest { latestTheme ->
            _theme.value = latestTheme
        }
    }

    private fun resetStopwatch() {
        startTime = 0L
        elapsedTime = 0L
        lastLapTime = "00:00:000"
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
            getUpdatedTime { milliseconds, seconds, minutes, hours ->
                _state.update {
                    it.copy(
                        milliSeconds = milliseconds,
                        seconds = seconds,
                        minutes = minutes,
                        hours = hours
                    )
                }
            }
            handler.postDelayed(this, 10)
        }
    }

    fun getUpdatedTime(updateTime: (String, String, String, String) -> Unit) {
        val totalMilliseconds = if (_state.value.stopwatchState == StopwatchState.Running) {
            elapsedTime + System.currentTimeMillis() - startTime
        } else {
            elapsedTime
        }

        val hours = (totalMilliseconds / 1000 / 60 / 60).toInt().toString().padStart(2, '0')
        val minutes = (totalMilliseconds / 1000 / 60).toInt().toString().padStart(2, '0')
        val seconds = (totalMilliseconds / 1000 % 60).toInt().toString().padStart(2, '0')
        val milliseconds = (totalMilliseconds % 1000).toInt().toString().padStart(3, '0')
        updateTime(milliseconds, seconds, minutes, hours)
    }

    data class StopwatchUiState(
        val stopwatchState: StopwatchState = StopwatchState.Ready,
        val milliSeconds: String = "000",
        val seconds: String = "00",
        val minutes: String = "00",
        val hours: String = "00",
        val lapList: List<Lap> = emptyList()
    )
}