package com.example.jiralogger.presentation.login

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.ui.theme.outlinedTextFieldColors
import com.example.jiralogger.presentation.util.InputFieldState

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val usernameState = viewModel.username.value
    val passwordState = viewModel.password.value
    Content(
        usernameState = usernameState,
        passwordState = passwordState,
        onEvent = { viewModel.onEvent(it) })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Content(
    usernameState: InputFieldState<String>,
    passwordState: InputFieldState<String>,
    onEvent: (LoginEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            inputState = usernameState,
            onValueChange = {
                onEvent(LoginEvent.UsernameEntered(it))
            },
            onClear = {
                onEvent(LoginEvent.ClearUsername)
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            inputState = passwordState,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                onEvent(LoginEvent.PasswordEntered(it))
            },
            onClear = {
                onEvent(LoginEvent.ClearPassword)
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        FilledTonalButton(onClick = { /*TODO*/ }) {
            Text(text = "Login")
        }
    }
}

@ExperimentalComposeUiApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TextField(
    inputState: InputFieldState<String>,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit
) {
    val onBackground = MaterialTheme.colorScheme.onBackground
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = inputState.value,
        onValueChange = onValueChange,
        isError = inputState.isErrorVisible,
        visualTransformation = visualTransformation,
        singleLine = true,
        textStyle = TextStyle(onBackground),
        label = {
            Text(text = inputState.hint, color = onBackground)
        },
        trailingIcon = {
            AnimatedVisibility(visible = inputState.value.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Icon(Icons.Default.Clear, null, tint = onBackground)
                }
            }
        },
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        colors = outlinedTextFieldColors()
    )
}

@Composable
@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun Preview() {
    val usernameState = InputFieldState(value = "", hint = "Username")
    val passwordState = InputFieldState(value = "", hint = "Password")
    JiraLoggerTheme {
        Content(
            usernameState = usernameState,
            passwordState = passwordState,
            onEvent = {}
        )
    }
}