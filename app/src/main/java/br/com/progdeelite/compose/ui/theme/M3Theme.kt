package br.com.progdeelite.compose.ui.theme

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

// MATERIAL YOU - MATERIAL 3 THEMING COLORS DAY/LIGHT
// downloaded: https://material-foundation.github.io/material-theme-builder/#/custom
//
// 1) COMO DEFINIR AS CORES DO SEU APPLICATIVO E COMO COPIALAS
// 2) ATUALIZAR DEPENDÊNCIAS NO BUILD GRADLE (gradle, colors, themes)
// 3) COMO REFATORAR DO MATERIAL 2 PARA O MATERIAL YOU OU MATERIAL 3
// 4) COMO INCREMENTAR NOSSO THEME ATUAL PARA DAR SUPORTE AO TEMA NOVO
// 5) VER RESULTADO NA PRÁTICA

// LINK PRO VIDEO: https://youtu.be/cnr68Gmr1O0 & https://youtu.be/73jIHwk-Td0

@Composable
fun ArsenalTheme(
    isDynamic: Boolean = true,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()
    val useDynamicColor = isDynamic && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val extendedColors = if (useDarkTheme) darkExtendedColors else lightExtendedColors

    val colors = if (useDynamicColor) {
        val context = LocalContext.current
        if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (useDarkTheme) DarkThemeColors else LightThemeColors
    }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colors.primary
        )
    }

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colors,
            typography = AppDefaultTypography,
            content = content
        )
    }
}

object ArsenalThemeExtended {
    val typography: AppCustomTypography
        @Composable
        get() = AppCustomTypography

    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}

// PREVIEWS PARA VISUALIZAR MELHOR AS CORES

@Preview
@Composable
fun LightColorsPreview() {
    ArsenalTheme(useDarkTheme = false) {
        ColorList()
    }
}

@Preview
@Composable
fun DarkColorsPreview() {
    ArsenalTheme(useDarkTheme = true) {
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
            "primary" to MaterialTheme.colorScheme.primary,
            "primaryVariant" to MaterialTheme.colorScheme.primaryContainer,
            "primarySurface" to MaterialTheme.colorScheme.onPrimaryContainer,
            "onPrimary" to MaterialTheme.colorScheme.onPrimary,
            "inversePrimary" to MaterialTheme.colorScheme.inversePrimary,

            "secondary" to MaterialTheme.colorScheme.secondary,
            "onSecondary" to MaterialTheme.colorScheme.onSecondary,
            "secondaryVariant" to MaterialTheme.colorScheme.secondaryContainer,
            "onSecondaryContainer" to MaterialTheme.colorScheme.onSecondaryContainer,

            "tertiary" to MaterialTheme.colorScheme.tertiary,
            "onTertiary" to MaterialTheme.colorScheme.onTertiary,
            "tertiaryContainer" to MaterialTheme.colorScheme.tertiaryContainer,
            "onTertiaryContainer" to MaterialTheme.colorScheme.onTertiaryContainer,

            "background" to MaterialTheme.colorScheme.background,
            "onBackground" to MaterialTheme.colorScheme.onBackground,

            "surface" to MaterialTheme.colorScheme.surface,
            "onSurface" to MaterialTheme.colorScheme.onSurface,
            "surfaceVariant" to MaterialTheme.colorScheme.surfaceVariant,
            "onSurfaceVariant" to MaterialTheme.colorScheme.onSurfaceVariant,
            "surfaceTint" to MaterialTheme.colorScheme.surfaceTint,
            "inverseSurface" to MaterialTheme.colorScheme.inverseSurface,
            "inverseOnSurface" to MaterialTheme.colorScheme.inverseOnSurface,

            "error" to MaterialTheme.colorScheme.error,
            "onError" to MaterialTheme.colorScheme.onError,
            "errorContainer" to MaterialTheme.colorScheme.errorContainer,
            "onErrorContainer" to MaterialTheme.colorScheme.onErrorContainer,
            "outline" to MaterialTheme.colorScheme.outline,

            // custom extented colors
            "customSnowWhite" to ArsenalThemeExtended.colors.snowWhite,
            "customDeepOcean" to ArsenalThemeExtended.colors.deepOcean,
            "customSkyBlue" to ArsenalThemeExtended.colors.skyBlue,
            "customNightBlue" to ArsenalThemeExtended.colors.nightBlue,
            "customDialogBackground" to ArsenalThemeExtended.colors.dialogBackground,
        ).forEach { (text, color) ->
            Row {
                Text(
                    text = text,
                    color = ArsenalThemeExtended.colors.snowWhite,
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