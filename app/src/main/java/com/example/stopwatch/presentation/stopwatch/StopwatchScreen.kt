package com.example.stopwatch.presentation.stopwatch

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stopwatch.data.model.Lap
import com.example.stopwatch.presentation.stopwatch.theme.ClassicStopwatch
import com.example.stopwatch.presentation.stopwatch.theme.DayStopwatch
import com.example.stopwatch.presentation.stopwatch.theme.NightStopwatch
import com.example.stopwatch.presentation.stopwatch.theme.OldSchoolStopwatch
import com.example.stopwatch.presentation.stopwatch.theme.WatchStopwatch
import com.example.stopwatch.util.Constants

@Composable
fun StopwatchScreen(
    viewModel: StopwatchViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val state by viewModel.state.collectAsState()
    val theme by viewModel.theme.collectAsState()

    val lapList = listOf(
        Lap(
            lapNumber = 1,
            intervalBtnPastLap = "00:00:00:000",
            lapTime = "00:00:00:000"
        ),
        Lap(
            lapNumber = 2,
            intervalBtnPastLap = "00:00:00:000",
            lapTime = "00:00:00:000"
        ),
        Lap(
            lapNumber = 3,
            intervalBtnPastLap = "00:00:00:000",
            lapTime = "00:00:00:000"
        ),
        Lap(
            lapNumber = 4,
            intervalBtnPastLap = "00:00:00:000",
            lapTime = "00:00:00:000"
        ),
        Lap(
            lapNumber = 5,
            intervalBtnPastLap = "00:00:00:000",
            lapTime = "00:00:00:000"
        ),
        Lap(
            lapNumber = 6,
            intervalBtnPastLap = "00:00:00:000",
            lapTime = "00:00:00:000"
        ),
        Lap(
            lapNumber = 7,
            intervalBtnPastLap = "00:00:00:000",
            lapTime = "00:00:00:000"
        ),
        Lap(
            lapNumber = 8,
            intervalBtnPastLap = "00:00:00:000",
            lapTime = "00:00:00:000"
        ),
        Lap(
            lapNumber = 9,
            intervalBtnPastLap = "00:00:00:000",
            lapTime = "00:00:00:000"
        ),
        Lap(
            lapNumber = 10,
            intervalBtnPastLap = "00:00:00:000",
            lapTime = "00:00:00:000"
        )
    )

    var secondIndicatorSize by remember {
        mutableStateOf(DpSize.Zero)
    }
    var minuteIndicatorSize by remember {
        mutableStateOf(DpSize.Zero)
    }
    var hourIndicatorSize by remember {
        mutableStateOf(DpSize.Zero)
    }

    LaunchedEffect(key1 = true) {
        val screenWidth = configuration.screenWidthDp.dp
        val screenHeight = configuration.screenHeightDp.dp / 2.2f

        hourIndicatorSize = DpSize(
            width = (screenWidth.value * 0.8f).dp,
            height = (screenHeight.value * 0.8f).dp
        )
        minuteIndicatorSize = DpSize(
            width = (screenWidth.value * 0.7f).dp,
            height = (screenHeight.value * 0.7f).dp
        )
        secondIndicatorSize = DpSize(
            width = (screenWidth.value * 0.6f).dp,
            height = (screenHeight.value * 0.6f).dp
        )
    }

    Column {

        when (theme) {
            Constants.AppTheme.DAY -> {
                DayStopwatch()
            }

            Constants.AppTheme.NIGHT -> {
                NightStopwatch()
            }

            Constants.AppTheme.WATCH -> {
                WatchStopwatch()
            }

            Constants.AppTheme.CLASSIC -> {
                ClassicStopwatch(
                    stopwatchState = state.stopwatchState,
                    seconds = state.seconds,
                    minutes = state.minutes,
                    hours = state.hours,
                    lapList = lapList,
                    onLapClick = { viewModel.onEvent(StopwatchEvent.AddLap) },
                    onResetClick = { viewModel.onEvent(StopwatchEvent.ResetStopwatch) },
                    onPauseClick = { viewModel.onEvent(StopwatchEvent.PauseStopwatch) },
                    onStartResumeClick = { viewModel.onEvent(StopwatchEvent.StartResumeStopwatch) },
                    onThemeClick = { viewModel.onEvent(StopwatchEvent.UpdateTheme(Constants.AppTheme.CLASSIC)) },
                    theme = theme
                )
            }

            Constants.AppTheme.OLD_SCHOOL -> {
                OldSchoolStopwatch()
            }
        }

    }
}