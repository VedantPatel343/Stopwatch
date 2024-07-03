package com.example.stopwatch.presentation.stopwatch.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stopwatch.R
import com.example.stopwatch.data.StopwatchState
import com.example.stopwatch.data.model.Lap
import com.example.stopwatch.presentation.common.Buttons

@Composable
fun ClassicStopwatch(
    modifier: Modifier = Modifier,
    stopwatchState: StopwatchState,
    seconds: String,
    minutes: String,
    hours: String,
    lapList: List<Lap>,
    theme: String,
    onThemeClick: () -> Unit,
    onLapClick: () -> Unit,
    onResetClick: () -> Unit,
    onPauseClick: () -> Unit,
    onStartResumeClick: () -> Unit
) {

    val playPauseIcon =
        if (stopwatchState == StopwatchState.Running) R.drawable.ic_pause else R.drawable.ic_play


    Column(Modifier.background(Color.Black.copy(alpha = 0.9f))) {

        Box(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(
                onClick = onThemeClick,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_theme),
                    contentDescription = "change theme",
                    tint = Color.Yellow
                )
            }
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = hours,
                    fontSize = 60.sp
                )
                Text(
                    text = "h",
                    fontSize = 20.sp,
                    color = Color.Yellow
                )
            }
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = minutes,
                    fontSize = 60.sp,
                    color = Color.Yellow
                )
                Text(
                    text = "m",
                    fontSize = 20.sp,
                    color = Color.Yellow
                )
            }
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = seconds,
                    fontSize = 60.sp,
                    color = Color.White
                )
                Text(
                    text = "s",
                    fontSize = 20.sp,
                    color = Color.Yellow
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .weight(3.5f)
        ) {
            items(lapList) { lap ->
                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        Text(text = "Lap ${lap.lapNumber}", fontSize = 20.sp)
                        Text(text = "+ ${lap.intervalBtnPastLap}", fontSize = 20.sp)
                        Text(text = lap.lapTime, fontSize = 20.sp)
                    }
                    Divider()
                }
            }
        }

        Buttons(
            stopwatchState = stopwatchState,
            playPauseIcon = playPauseIcon,
            onStartResumeClick = onStartResumeClick,
            onLapClick = onLapClick,
            onResetClick = onResetClick,
            onPauseClick = onPauseClick,
            buttonColor = Color.Yellow,
            playPauseIconColor = Color.Black.copy(alpha = 0.9f)
            )

    }
}

