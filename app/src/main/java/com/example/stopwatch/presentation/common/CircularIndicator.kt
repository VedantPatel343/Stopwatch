package com.example.stopwatch.presentation.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize

@Composable
fun CircularIndicator(
    modifier: Modifier = Modifier,
    canvasSize: DpSize = DpSize.Zero,
    currentIndicatorValue: Int,
    maxIndicatorValue: Int,
    animationDuration: Int = 1000,
    backgroundIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    backgroundIndicatorStrokeWidth: Float = 100f,
    foregroundIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    foregroundIndicatorStrokeWidth: Float = 100f
) {

    val animatedIndicatorValue = remember { Animatable(initialValue = 0f) }
    LaunchedEffect(key1 = currentIndicatorValue) {
        animatedIndicatorValue.animateTo(targetValue = currentIndicatorValue.toFloat())
    }

    val percentage = (currentIndicatorValue / maxIndicatorValue.toFloat()) * 100

    val sweepAngle by animateFloatAsState(
        targetValue = (3.6 * percentage).toFloat(),
        animationSpec = tween(animationDuration),
        label = "sweepAngle"
    )

    Box(
        modifier = modifier.then(
            Modifier
                .size(canvasSize)
                .drawBehind {
                    val componentSize = size
                    backgroundIndicator(
                        componentSize = componentSize,
                        indicatorColor = backgroundIndicatorColor,
                        indicatorStrokeWidth = backgroundIndicatorStrokeWidth
                    )
                    foregroundIndicator(
                        sweepAngle = sweepAngle,
                        componentSize = componentSize,
                        indicatorColor = foregroundIndicatorColor,
                        indicatorStrokeWidth = foregroundIndicatorStrokeWidth
                    )
                }
        )
    ) {

    }

}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 270f,
        sweepAngle = 360f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 270f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CircularIndicator(
        currentIndicatorValue = 40,
        maxIndicatorValue = 100
    )
}