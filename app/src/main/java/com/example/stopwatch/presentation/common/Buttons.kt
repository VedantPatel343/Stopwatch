package com.example.stopwatch.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.stopwatch.R
import com.example.stopwatch.data.StopwatchState

@Composable
fun Buttons(
    modifier: Modifier = Modifier,
    stopwatchState: StopwatchState,
    playPauseIcon: Int,
    buttonColor: Color,
    onStartResumeClick: () -> Unit,
    onLapClick: () -> Unit,
    onResetClick: () -> Unit,
    onPauseClick: () -> Unit,
    playPauseIconColor: Color
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        AnimatedVisibility(
            visible = stopwatchState == StopwatchState.Running || stopwatchState == StopwatchState.Paused
        ) {
            IconButton(
                modifier = Modifier
                    .background(Color.Gray, shape = RoundedCornerShape(70.dp))
                    .size(60.dp),
                onClick = onLapClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_lap),
                    contentDescription = "Map Btn",
                    tint = Color.White,
                    modifier = Modifier.width(30.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(20.dp))

        IconButton(
            modifier = Modifier
                .background(buttonColor, shape = RoundedCornerShape(100.dp))
                .size(80.dp),
            onClick = if (stopwatchState == StopwatchState.Running) {
                onPauseClick
            } else {
                onStartResumeClick
            }
        ) {
            Icon(
                painter = painterResource(id = playPauseIcon),
                contentDescription = "playPause Btn",
                tint = playPauseIconColor,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        AnimatedVisibility(visible = stopwatchState == StopwatchState.Running || stopwatchState == StopwatchState.Paused) {
            IconButton(
                modifier = Modifier
                    .background(Color.Gray, shape = RoundedCornerShape(70.dp))
                    .size(60.dp),
                onClick = onResetClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_reset),
                    contentDescription = "Reset Btn",
                    tint = Color.White,
                    modifier = Modifier.width(30.dp)
                )
            }
        }
    }

}