package br.com.progdeelite.compose.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComposeArsenalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val extendedColors = if (darkTheme) darkExtendedColors else lightExtendedColors

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colors = if (darkTheme) darkMaterialColors else lightMaterialColors,
            typography = AppDefaultTypography,
            shapes = DefaultShapes,
            content = content
        )
    }
}

object ComposeArsenalTheme {
    val typographyCustom: AppCustomTypography
        @Composable
        get() = AppCustomTypography

    val colorsCustom: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}

// PREVIEWS PARA VISUALIZAR MELHOR AS CORES

@Preview
@Composable
fun LightColorsPreview() {
    ComposeArsenalTheme(darkTheme = false) {
        ColorList()
    }
}

@Preview
@Composable
fun DarkColorsPreview() {
    ComposeArsenalTheme(darkTheme = true) {
        ColorList()
    }
}

@Composable
fun ColorList() {
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .fillMaxHeight()
    ) {
        mapOf(
            "primary" to MaterialTheme.colors.primary,
            "primaryVariant" to MaterialTheme.colors.primaryVariant,
            "primarySurface" to MaterialTheme.colors.primarySurface,
            "onPrimary" to MaterialTheme.colors.onPrimary,

            "secondary" to MaterialTheme.colors.secondary,
            "onSecondary" to MaterialTheme.colors.onSecondary,
            "secondaryVariant" to MaterialTheme.colors.secondaryVariant,

            "surface" to MaterialTheme.colors.surface,
            "onSurface" to MaterialTheme.colors.onSurface,

            "error" to MaterialTheme.colors.error,
            "background" to MaterialTheme.colors.background,
            "onBackground" to MaterialTheme.colors.onBackground,

            // custom extented colors
            "customSnowWhite" to ComposeArsenalTheme.colorsCustom.snowWhite,
            "customDeepOcean" to ComposeArsenalTheme.colorsCustom.deepOcean,
            "customSkyBlue" to ComposeArsenalTheme.colorsCustom.skyBlue,
            "customNightBlue" to ComposeArsenalTheme.colorsCustom.nightBlue,
            "customDialogBackground" to ComposeArsenalTheme.colorsCustom.dialogBackground,
        ).forEach { (text, color) ->
            Row {
                Text(
                    text = text,
                    color = ComposeArsenalTheme.colorsCustom.snowWhite,
                    modifier = Modifier
                        .weight(1f)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(32.dp)
                        .background(color)
                )
            }
        }
    }
}