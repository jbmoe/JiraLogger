package com.example.jiralogger.presentation.util

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import coil.imageLoader
import coil.request.ImageRequest

@Composable
fun ImageFromUrl(
    url: String?,
    @DrawableRes placeholder: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier
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
