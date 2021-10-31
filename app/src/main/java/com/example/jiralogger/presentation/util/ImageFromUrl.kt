package com.example.jiralogger.presentation.util

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import coil.imageLoader
import coil.request.ImageRequest
import com.example.jiralogger.R
import com.example.jiralogger.presentation.ui.theme.JiraLoggerTheme

@Composable
fun ImageFromUrl(
    modifier: Modifier = Modifier,
    url: String = "",
    @DrawableRes placeholder: Int,
    contentDescription: String = ""
) {
    val req = ImageRequest
        .Builder(LocalContext.current)
        .placeholder(placeholder)
        .data(url)//.decoder(SvgDecoder(LocalContext.current))
        .build()
    val imageLoader = LocalContext.current.imageLoader
    Image(
        painter = rememberImagePainter(request = req, imageLoader = imageLoader),
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun Preview() {
    JiraLoggerTheme {
        ImageFromUrl(placeholder = R.drawable.default_project_avatar)
    }
}