package com.example.jiralogger.presentation.work_log_list.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import com.example.jiralogger.R
import com.example.jiralogger.domain.util.OrderType
import com.example.jiralogger.presentation.components.Text

@Composable
fun OrderDropDown(onSelection: (OrderType) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    fun dismiss() {
        expanded = false
    }

    fun toggleExp() {
        expanded = !expanded
    }

    IconButton(onClick = { toggleExp() }) {
        Icon(painterResource(id = R.drawable.ic_baseline_sort_24), "Sort")
    }

    DropdownMenu(expanded = expanded, onDismissRequest = { dismiss() }) {
        DropdownMenuItem(onClick = {
            dismiss()
            onSelection(OrderType.Ascending)
        }) {
            Text(text = "Ascending")
        }
        DropdownMenuItem(onClick = {
            dismiss()
            onSelection(OrderType.Descending)
        }) {
            Text(text = "Descending")
        }
    }


}