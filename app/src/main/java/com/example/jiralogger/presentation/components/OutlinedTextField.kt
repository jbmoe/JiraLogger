package com.example.jiralogger.presentation.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.example.jiralogger.presentation.ui.theme.outlinedTextFieldColors
import com.example.jiralogger.presentation.util.InputFieldState

@ExperimentalComposeUiApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean = false,
    labelText: String = "",
    placeholderText: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    val onBackground = MaterialTheme.colorScheme.onBackground
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        visualTransformation = visualTransformation,
        textStyle = TextStyle(onBackground),
        label = textOrNull(labelText),
        singleLine = singleLine,
        maxLines = maxLines,
        readOnly = readOnly,
        placeholder = textOrNull(placeholderText),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        colors = outlinedTextFieldColors()
    )
}

@ExperimentalComposeUiApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OutlinedTextField(
    modifier: Modifier = Modifier,
    inputState: InputFieldState<String>,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = inputState.value,
        labelText = inputState.label,
        placeholderText = inputState.placeholder,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        maxLines = maxLines,
        readOnly = readOnly,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onValueChange = onValueChange
    )
}


@Composable
private fun textOrNull(text: String): @Composable (() -> Unit)? {
    return if (text.isNotBlank()) {
        { Text(text = text, color = MaterialTheme.colorScheme.onBackground) }
    } else {
        null
    }
}