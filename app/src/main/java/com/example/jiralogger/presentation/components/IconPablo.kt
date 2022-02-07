package com.example.jiralogger.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

@Composable
fun IconPablo(
    bitmap: ImageBitmap,
    modifier: Modifier = Modifier,
    description: String? = null,
    tint: Color = LocalContentColor.current
) {
    Icon(
        bitmap = bitmap,
        contentDescription = description,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IconPablo(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    description: String? = null,
    tint: Color = LocalContentColor.current
) {
    Icon(
        imageVector = imageVector,
        contentDescription = description,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IconPablo(
    @DrawableRes resourceId: Int,
    modifier: Modifier = Modifier,
    description: String? = null,
    tint: Color = LocalContentColor.current
) {
    Icon(
        painter = painterResource(resourceId),
        contentDescription = description,
        modifier = modifier,
        tint = tint
    )
}