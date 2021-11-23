package com.example.jiralogger.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.ui.theme.outlinedTextFieldColors
import com.example.jiralogger.presentation.util.InputFieldState

@ExperimentalComposeUiApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    isError: Boolean = false,
    errorText: String = "",
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

    Column {
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
            trailingIcon = trailingIconOrError(trailingIcon = trailingIcon, isError = isError),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            colors = outlinedTextFieldColors()
        )
        if (isError and errorText.isNotEmpty()) {
            Text(text = errorText, color = MaterialTheme.colorScheme.error)
        }
    }
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
private fun textOrNull(text: String): @Composable (() -> Unit)? {
    return if (text.isNotBlank()) {
        { Text(text = text) }
    } else {
        null
    }
}

@Composable
fun trailingIconOrError(
    trailingIcon: @Composable (() -> Unit)?,
    isError: Boolean
): @Composable (() -> Unit)? {
    return if (isError) {
        { Icon(Icons.Default.Warning, "error", tint = MaterialTheme.colorScheme.error) }
    } else {
        trailingIcon
    }
}

@ExperimentalComposeUiApi
@Composable
@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun PreviewOTF() {
    val username = InputFieldState(
        value = "",
        label = "Username",
        placeholder = "Your username",
        error = "Wrong username",
        isError = false,
    )
    val password = InputFieldState(
        value = "",
        label = "Password",
        placeholder = "Your password",
        error = "Wrong password",
        isError = true,
    )
    JiraLoggerTheme {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column {
                OutlinedTextField(
                    value = username.value,
                    labelText = username.label,
                    placeholderText = username.placeholder,
                    isError = username.isError,
                    errorText = username.error,
                    onValueChange = {}
                )
                OutlinedTextField(
                    value = password.value,
                    labelText = password.label,
                    placeholderText = password.placeholder,
                    isError = password.isError,
                    errorText = password.error,
                    onValueChange = {}
                )
            }
        }
    }
}