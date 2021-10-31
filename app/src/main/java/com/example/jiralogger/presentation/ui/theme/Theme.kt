package com.example.jiralogger.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

//private val DarkColorPalette = darkColors(
//    primary = ColorPrimary,
//    background = DarkGray,
//    onBackground = TextWhite,
//    onPrimary = DarkGray
//)
//
//private val LightColorPalette = lightColors(
//    primary = ColorPrimary,
//    background = Color.White,
//    onBackground = MediumGray,
//    onPrimary = DarkGray
//)
//
//private val DarkColorPalette2 = darkColors(
//    surface = Blue,
//    onSurface = Navy,
//    primary = Navy,
//    onPrimary = Chartreuse
//)
//
//private val LightColorPalette2 = lightColors(
//    surface = Blue,
//    onSurface = Color.White,
//    primary = LightBlue,
//    onPrimary = Navy
//)
//
//private val YellowBlue = lightColors(
//    primary = primaryColor,
//    onPrimary = primaryTextColor,
//    secondary = secondaryColor,
//    onSecondary = secondaryTextColor
//)
//
//private val LightColors = lightColors(
//    primary = Red700,
//    primaryVariant = Red900,
//    onPrimary = Color.White,
//    secondary = Red700,
//    secondaryVariant = Red900,
//    onSecondary = Color.White,
//    error = Red800
//)
//
//private val DarkColors = darkColors(
//    primary = Red300,
//    primaryVariant = Red700,
//    onPrimary = Color.Black,
//    secondary = Red300,
//    onSecondary = Color.Black,
//    error = Red200
//)

private val LightScheme = lightColorScheme(

)

private val DarkScheme = darkColorScheme(

)

@Composable
fun JiraLoggerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val scheme = if (darkTheme) {
        DarkScheme
    } else {
        LightScheme
    }

    MaterialTheme(
        colorScheme = scheme,
        content = content
    )
}