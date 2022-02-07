package com.example.jiralogger.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jiralogger.R
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@Composable
fun NumberPicker(
    value: Int,
    suffix: String = "",
    onChange: (NumberPickerEvent) -> Unit
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val color = MaterialTheme.colorScheme.onBackground
        IconButton(
            onClick = {
                onChange(NumberPickerEvent.Increase)
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
            PabloText(text = "$value", style = textStyle)
            PabloText(text = suffix, style = textStyle)
        }
        IconButton(modifier = Modifier, onClick = {
            onChange(NumberPickerEvent.Decrease)
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

sealed class NumberPickerEvent {
    object Increase: NumberPickerEvent()
    object Decrease: NumberPickerEvent()
}

@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewNumberPicker() {
    JiraLoggerTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val hours = (0..99).toList()
            NumberPicker(
                value = 1,
                suffix = "",
                onChange = {})
        }
    }
}