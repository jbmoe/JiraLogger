package com.example.jiralogger.presentation.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ContentAlpha
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.jiralogger.presentation.components.Text

@Composable
fun outlinedTextFieldColors(
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    disabledTextColor: Color = textColor.copy(ContentAlpha.disabled),
    backgroundColor: Color = Color.Transparent,
    cursorColor: Color = MaterialTheme.colorScheme.primary,
    errorCursorColor: Color = MaterialTheme.colorScheme.error,
    focusedBorderColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high),
    unfocusedBorderColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
    disabledBorderColor: Color = unfocusedBorderColor.copy(alpha = ContentAlpha.disabled),
    errorBorderColor: Color = MaterialTheme.colorScheme.error,
    leadingIconColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
    disabledLeadingIconColor: Color = leadingIconColor.copy(alpha = ContentAlpha.disabled),
    errorLeadingIconColor: Color = leadingIconColor,
    trailingIconColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
    disabledTrailingIconColor: Color = trailingIconColor.copy(alpha = ContentAlpha.disabled),
    errorTrailingIconColor: Color = MaterialTheme.colorScheme.error,
    focusedLabelColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high),
    unfocusedLabelColor: Color = MaterialTheme.colorScheme.onSurface.copy(ContentAlpha.medium),
    disabledLabelColor: Color = unfocusedLabelColor.copy(ContentAlpha.disabled),
    errorLabelColor: Color = MaterialTheme.colorScheme.error,
    placeholderColor: Color = MaterialTheme.colorScheme.onSurface.copy(ContentAlpha.medium),
    disabledPlaceholderColor: Color = placeholderColor.copy(ContentAlpha.disabled)
): TextFieldColors =
    MaterialThemeOutlinedTextFieldColors(
        textColor = textColor,
        disabledTextColor = disabledTextColor,
        cursorColor = cursorColor,
        errorCursorColor = errorCursorColor,
        focusedIndicatorColor = focusedBorderColor,
        unfocusedIndicatorColor = unfocusedBorderColor,
        errorIndicatorColor = errorBorderColor,
        disabledIndicatorColor = disabledBorderColor,
        leadingIconColor = leadingIconColor,
        disabledLeadingIconColor = disabledLeadingIconColor,
        errorLeadingIconColor = errorLeadingIconColor,
        trailingIconColor = trailingIconColor,
        disabledTrailingIconColor = disabledTrailingIconColor,
        errorTrailingIconColor = errorTrailingIconColor,
        backgroundColor = backgroundColor,
        focusedLabelColor = focusedLabelColor,
        unfocusedLabelColor = unfocusedLabelColor,
        disabledLabelColor = disabledLabelColor,
        errorLabelColor = errorLabelColor,
        placeholderColor = placeholderColor,
        disabledPlaceholderColor = disabledPlaceholderColor
    )


@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview() {
    JiraLoggerTheme {

        Column {
            Text(
                text = "displayLarge",
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                text = "displayMedium",
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = "displaySmall",
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = "headlineLarge",
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "headlineMedium",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "headlineSmall",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "titleLarge",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "titleMedium",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "titleSmall",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "bodyLarge",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "bodyMedium",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "bodySmall",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "labelLarge",
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = "labelMedium",
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = "labelSmall",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}