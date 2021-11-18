package com.example.jiralogger.presentation.login

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jiralogger.R
import com.example.jiralogger.presentation.components.OutlinedTextField
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme
import com.example.jiralogger.presentation.util.InputFieldState
import com.example.jiralogger.presentation.util.Screen

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
        onEvent = { viewModel.onEvent(it) },
        login = {
            if (viewModel.onEvent(LoginEvent.Login))
                navController.navigate(Screen.IssueListScreen.route)
        }
    )
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun Content(
    usernameState: InputFieldState<String>,
    passwordState: InputFieldState<String>,
    login: () -> Unit,
    onEvent: (LoginEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        OutlinedTextField(
            inputState = usernameState,
            singleLine = true,
            onValueChange = {
                onEvent(LoginEvent.UsernameEntered(it))
            },
            trailingIcon = animatedClearButton(usernameState.value.isNotEmpty()) {
                onEvent(LoginEvent.ClearUsername)
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            inputState = passwordState,
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                onEvent(LoginEvent.PasswordEntered(it))
            },
            trailingIcon = animatedClearButton(passwordState.value.isNotEmpty()) {
                onEvent(LoginEvent.ClearPassword)
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        FilledTonalButton(onClick = login) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.padding(bottom = 128.dp))
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun animatedClearButton(visible: Boolean, onClick: () -> Unit): @Composable (() -> Unit) {
    return {
        AnimatedVisibility(visible = visible) {
            IconButton(onClick = onClick) {
                Icon(
                    Icons.Default.Clear,
                    null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
@Preview(name = "Light Mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun Preview() {
    val usernameState = InputFieldState(value = "", label = "Username")
    val passwordState = InputFieldState(value = "", label = "Password")
    JiraLoggerTheme {
        Content(
            usernameState = usernameState,
            passwordState = passwordState,
            onEvent = {},
            login = {}
        )
    }
}