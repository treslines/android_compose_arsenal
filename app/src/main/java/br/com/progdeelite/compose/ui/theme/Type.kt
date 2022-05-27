package br.com.progdeelite.compose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val AppDefaultTypography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

object AppCustomTypography {

    val h1: TextStyle
        @Composable
        get() = MaterialTheme.typography.h4.copy(
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
        )

    val h2: TextStyle
        @Composable
        get() = MaterialTheme.typography.h6.copy(
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
        )

    val body1: TextStyle
        @Composable
        get() = MaterialTheme.typography.body1.copy(
            color = MaterialTheme.colors.primary,
        )

    val body2: TextStyle
        @Composable
        get() = MaterialTheme.typography.body2.copy(
            color = MaterialTheme.colors.primary,
        )
}

val TextStyle.bold: TextStyle
    @Composable
    get() = AppCustomTypography.body1.copy(fontWeight = FontWeight.Bold)