package com.delarosa.composeapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val WhiteGray = Color(0xFFF0E9EB)
val GrayBlack = Color(0xFF0C0C0C)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

@Composable
fun textColorContentStyle() = WhiteGray

@Composable
fun textColorTitleStyle() = Color.White

@Composable
fun textBodyLargeStyle() = MaterialTheme.typography.bodyLarge