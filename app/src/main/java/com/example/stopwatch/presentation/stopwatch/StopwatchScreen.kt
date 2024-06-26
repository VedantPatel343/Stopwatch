package com.example.stopwatch.presentation.stopwatch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stopwatch.R
import com.example.stopwatch.data.StopwatchState

@Composable
fun StopwatchScreen(
    viewModel: StopwatchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val mapResetIcon =
        if (state.stopwatchState == StopwatchState.Running) R.drawable.ic_lap else R.drawable.ic_stop
    val playPauseIcon =
        if (state.stopwatchState == StopwatchState.Running) R.drawable.ic_pause else R.drawable.ic_play

    Column {
        Text(
            text = "${state.minutes}:${state.seconds}:${state.milliSeconds}",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            AnimatedVisibility(visible = state.stopwatchState == StopwatchState.Ready) {
                IconButton(
                    modifier = Modifier
                        .width(100.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(50.dp))
                        .size(50.dp),
                    onClick = { viewModel.onEvent(StopwatchEvent.StartStopwatch) }
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Start Btn",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        AnimatedVisibility(visible = state.stopwatchState == StopwatchState.Running || state.stopwatchState == StopwatchState.Paused) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                IconButton(
                    modifier = Modifier
                        .background(Color.Gray, shape = RoundedCornerShape(50.dp))
                        .size(50.dp),
                    onClick = {
                        if (state.stopwatchState == StopwatchState.Running) {
                            viewModel.onEvent(StopwatchEvent.AddLap)
                        } else {
                            viewModel.onEvent(StopwatchEvent.ResetStopwatch)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(mapResetIcon),
                        contentDescription = "Map-Reset Btn",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                IconButton(
                    modifier = Modifier
                        .background(Color.Gray, shape = RoundedCornerShape(50.dp))
                        .size(50.dp),
                    onClick = {
                        if (state.stopwatchState == StopwatchState.Running) {
                            viewModel.onEvent(StopwatchEvent.PauseStopwatch)
                        } else {
                            viewModel.onEvent(StopwatchEvent.StartStopwatch)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(playPauseIcon),
                        contentDescription = "Play-Pause Btn",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

    }
}