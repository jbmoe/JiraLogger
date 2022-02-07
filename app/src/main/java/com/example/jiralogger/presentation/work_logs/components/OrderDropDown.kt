package com.example.jiralogger.presentation.work_logs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.jiralogger.R
import com.example.jiralogger.domain.util.OrderType
import com.example.jiralogger.presentation.components.PabloText

@Composable
fun OrderDropDown(currentOrderType: OrderType, onSelection: (OrderType) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = !expanded }) {
        Icon(painterResource(id = R.drawable.ic_baseline_sort_24), "Order")
    }

    val background = MaterialTheme.colorScheme.background.copy(.9f)
    val contentColor = MaterialTheme.colorScheme.onBackground.copy(.8f)
    val radioButtonColors = RadioButtonDefaults.colors(
        selectedColor = MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high),
        unselectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.background(background)
    ) {
        val ascending = { onSelection(OrderType.Ascending) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { ascending() }) {
            RadioButton(
                selected = currentOrderType is OrderType.Ascending,
                onClick = ascending,
                colors = radioButtonColors
            )
            PabloText(text = "Ascending", color = contentColor)
        }
        val descending = { onSelection(OrderType.Descending) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { descending() }) {
            RadioButton(
                selected = currentOrderType is OrderType.Descending,
                onClick = descending,
                colors = radioButtonColors
            )
            PabloText(text = "Descending", color = contentColor)
        }
    }
}