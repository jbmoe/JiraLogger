package com.example.jiralogger.presentation.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import com.example.jiralogger.presentation.ui.theme.outlinedTextFieldColors
import com.example.jiralogger.presentation.util.InputFieldState

@ExperimentalComposeUiApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PabloTF(
    modifier: Modifier = Modifier,
    value: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    errorText: String = "",
    labelText: String? = null,
    placeholderText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
) {
    val onBackground = MaterialTheme.colorScheme.onBackground
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            enabled = enabled,
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
            trailingIcon = trailingIconOrError(trailingIcon = trailingIcon, isError = isError),
            keyboardOptions = keyboardOptions.copy(capitalization = KeyboardCapitalization.Sentences),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            colors = outlinedTextFieldColors()
        )
        if (isError and errorText.isNotEmpty()) {
            PabloText(text = errorText, color = MaterialTheme.colorScheme.error)
        }
    }
}

@ExperimentalComposeUiApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PabloTF(
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
    PabloTF(
        modifier = modifier,
        value = inputState.value,
        labelText = inputState.label,
        placeholderText = inputState.placeholder,
        isError = inputState.isError,
        errorText = inputState.error,
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
private fun textOrNull(text: String?): @Composable (() -> Unit)? {
    return if (text.isNullOrBlank()) {
        null
    } else {
        { PabloText(text = text) }
    }
}

@Composable
private fun trailingIconOrError(
    trailingIcon: @Composable (() -> Unit)?,
    isError: Boolean
): @Composable (() -> Unit)? {
    return if (isError) {
        { IconPablo(imageVector = Icons.Default.Warning, tint = MaterialTheme.colorScheme.error) }
    } else {
        trailingIcon
    }
}
