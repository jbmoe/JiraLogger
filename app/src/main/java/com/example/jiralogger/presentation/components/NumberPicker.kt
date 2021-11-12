package com.example.jiralogger.presentation.components

import android.content.res.Configuration
import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.jiralogger.R
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun NumberPicker(
    state: MutableState<Int>,
    modifier: Modifier = Modifier,
    range: List<Int>? = null,
    labelSuffix: String = "",
    textStyle: TextStyle = LocalTextStyle.current,
    onStateChanged: (Int) -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    val numbersColumnHeight = 36.dp
    val halvedNumbersColumnHeight = numbersColumnHeight / 2
    val halvedNumbersColumnHeightPx =
        with(LocalDensity.current) { halvedNumbersColumnHeight.toPx() }

    fun animatedStateValue(offset: Float): Int =
        state.value - (offset / halvedNumbersColumnHeightPx).toInt()

    val animatedOffset = remember { Animatable(0f) }.apply {
        if (range != null) {
            val offsetRange = remember(state.value, range) {
                val value = state.value
                val first = -(range.last() - value) * halvedNumbersColumnHeightPx
                val last = -(range.first() - value) * halvedNumbersColumnHeightPx
                first..last
            }
            updateBounds(offsetRange.start, offsetRange.endInclusive)
        }
    }
    val coercedAnimatedOffset = animatedOffset.value % halvedNumbersColumnHeightPx
    val animatedStateValue = animatedStateValue(animatedOffset.value)

    Column(
        modifier = modifier
            .wrapContentSize()
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { deltaY ->
                    coroutineScope.launch {
                        animatedOffset.snapTo(animatedOffset.value + deltaY)
                    }
                },
                onDragStopped = { velocity ->
                    coroutineScope.launch {
                        val endValue = animatedOffset.fling(
                            initialVelocity = velocity,
                            animationSpec = exponentialDecay(frictionMultiplier = 20f),
                            adjustTarget = { target ->
                                val coercedTarget = target % halvedNumbersColumnHeightPx
                                val coercedAnchors = listOf(
                                    -halvedNumbersColumnHeightPx,
                                    0f,
                                    halvedNumbersColumnHeightPx
                                )
                                val coercedPoint =
                                    coercedAnchors.minByOrNull { abs(it - coercedTarget) }!!
                                val base =
                                    halvedNumbersColumnHeightPx * (target / halvedNumbersColumnHeightPx).toInt()
                                coercedPoint + base
                            }
                        ).endState.value

                        state.value = animatedStateValue(endValue)
                        onStateChanged(state.value)
                        animatedOffset.snapTo(0f)
                    }
                }
            )
    ) {
        val spacing = 4.dp

        Icon(
            painterResource(id = R.drawable.ic_baseline_arrow_drop_up_24), "",
            tint = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(spacing))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .offset { IntOffset(x = 0, y = coercedAnimatedOffset.roundToInt()) }
        ) {
            val baseLabelModifier = Modifier.align(Alignment.Center)
            ProvideTextStyle(textStyle) {
                Label(
                    text = (animatedStateValue - 1).toString() + labelSuffix,
                    modifier = baseLabelModifier
                        .offset(y = -halvedNumbersColumnHeight)
                        .alpha(coercedAnimatedOffset / halvedNumbersColumnHeightPx)
                )
                Label(
                    text = animatedStateValue.toString() + labelSuffix,
                    modifier = baseLabelModifier
                        .alpha(1 - abs(coercedAnimatedOffset) / halvedNumbersColumnHeightPx)
                )
                Label(
                    text = (animatedStateValue + 1).toString() + labelSuffix,
                    modifier = baseLabelModifier
                        .offset(y = halvedNumbersColumnHeight)
                        .alpha(-coercedAnimatedOffset / halvedNumbersColumnHeightPx)
                )
            }
        }

        Spacer(modifier = Modifier.height(spacing))

        Icon(
            painterResource(id = R.drawable.ic_baseline_arrow_drop_down_24),
            "",
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun Label(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures(onLongPress = {
                // Empty to disable text selection
            })
        },
        color = MaterialTheme.colorScheme.onBackground
    )
}

private suspend fun Animatable<Float, AnimationVector1D>.fling(
    initialVelocity: Float,
    animationSpec: DecayAnimationSpec<Float>,
    adjustTarget: ((Float) -> Float)?,
    block: (Animatable<Float, AnimationVector1D>.() -> Unit)? = null,
): AnimationResult<Float, AnimationVector1D> {
    val targetValue = animationSpec.calculateTargetValue(value, initialVelocity)
    val adjustedTarget = adjustTarget?.invoke(targetValue)

    return if (adjustedTarget != null) {
        animateTo(
            targetValue = adjustedTarget,
            initialVelocity = initialVelocity,
            block = block
        )
    } else {
        animateDecay(
            initialVelocity = initialVelocity,
            animationSpec = animationSpec,
            block = block,
        )
    }
}

@Composable
fun NumberPickz(numbers: List<Int>, suffix: String = "", onChange: (Int) -> Unit) {
    var index by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val color = MaterialTheme.colorScheme.onBackground
        IconButton(
            onClick = {
                if (numbers.size > index + 1)
                    index++
                else
                    index = 0
                onChange(numbers[index])
            }
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.ic_baseline_arrow_drop_up_24),
                tint = color,
                contentDescription = "Increase"
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val textStyle = MaterialTheme.typography.bodyLarge

            Text(text = "${numbers[index]}", style = textStyle, color = color)
            Text(text = suffix, style = textStyle, color = color)
        }

        IconButton(modifier = Modifier, onClick = {
            if (index > 0)
                index--
            else
                index = numbers.size - 1
            onChange(numbers[index])
        }) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.ic_baseline_arrow_drop_down_24),
                tint = color,
                contentDescription = "Decrease"
            )
        }
    }
}

@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewNumberPicker() {
    JiraLoggerTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val hours = (0..99).toList()
            NumberPickz(numbers = hours, suffix = "", onChange = {})
        }
    }
}