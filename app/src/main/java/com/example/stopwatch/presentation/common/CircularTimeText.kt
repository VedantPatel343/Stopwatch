package com.example.stopwatch.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CircularTimeText(
    modifier: Modifier = Modifier,
    minutes: String,
    millis: String,
    seconds: String,
    hours: String
) {

    Column(modifier = modifier) {

        if (hours.toInt() >= 1) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "hours",
                    fontSize = 20.sp
                )
                Text(
                    text = hours,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "min",
                    fontSize = 20.sp
                )
                Text(
                    text = minutes,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "",
                    fontSize = 20.sp
                )
                Text(
                    text = ":",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "sec",
                    fontSize = 20.sp
                )
                Text(
                    text = seconds,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = millis,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

    }
}