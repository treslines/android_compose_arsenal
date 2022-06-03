//package br.com.progdeelite.compose.ui.theme
//
//import androidx.compose.material.Colors
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.Immutable
//import androidx.compose.runtime.staticCompositionLocalOf
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import br.com.progdeelite.compose.R
//
//// day colors - cores para usar durante o dia
//val lightMaterialColors: Colors
//    @Composable
//    get() = Colors(
//        primary = colorResource(id = R.color.primaryColor),
//        primaryVariant = colorResource(id = R.color.primaryLightColor),
//        onPrimary = colorResource(id = R.color.onPrimaryColor),
//
//        secondary = colorResource(id = R.color.secondaryColor),
//        secondaryVariant = colorResource(id = R.color.secondaryLightColor),
//        onSecondary = colorResource(id = R.color.onSecondaryColor),
//
//        background = colorResource(id = R.color.backgroundColor),
//        onBackground = colorResource(id = R.color.onBackgroundColor),
//
//        surface = colorResource(id = R.color.surfaceColor),
//        onSurface = colorResource(id = R.color.onSurfaceColor),
//
//        error = colorResource(id = R.color.errorColor),
//        onError = colorResource(id = R.color.onErrorColor),
//
//        isLight = true
//    )
//
//// night colors - cores para usar a noite
//val darkMaterialColors: Colors
//    @Composable
//    get() = Colors(
//        primary = colorResource(id = R.color.primaryColor),
//        primaryVariant = colorResource(id = R.color.primaryLightColor),
//        onPrimary = colorResource(id = R.color.onPrimaryColor),
//
//        secondary = colorResource(id = R.color.secondaryColor),
//        secondaryVariant = colorResource(id = R.color.secondaryLightColor),
//        onSecondary = colorResource(id = R.color.onSecondaryColor),
//
//        background = colorResource(id = R.color.backgroundColor),
//        onBackground = colorResource(id = R.color.onBackgroundColor),
//
//        surface = colorResource(id = R.color.surfaceColor),
//        onSurface = colorResource(id = R.color.onSurfaceColor),
//
//        error = colorResource(id = R.color.errorColor),
//        onError = colorResource(id = R.color.onErrorColor),
//
//        isLight = false
//    )
//
///**
// * Base class to extend the material color theme with auxiliary colors that goes
// * beyond the default theme acc. to accomplish your company's corporate identity design
// */
//@Immutable
//data class ExtendedColors(
//    val snowWhite: Color,
//    val deepOcean: Color,
//    val skyBlue: Color,
//    val nightBlue: Color,
//    val dialogBackground: Color
//)
//
//// cores que serão usados de acordo com a modalidade dia ou noite
//val LocalExtendedColors = staticCompositionLocalOf {
//    ExtendedColors(
//        snowWhite = Color.Unspecified,
//        deepOcean = Color.Unspecified,
//        skyBlue = Color.Unspecified,
//        nightBlue = Color.Unspecified,
//        dialogBackground = Color.Unspecified
//    )
//}
//
//// day colors extented - cores auxiliares para dia que vão além do padrão
//val lightExtendedColors: ExtendedColors
//    @Composable
//    get() = ExtendedColors(
//        snowWhite = colorResource(id = R.color.white_snow),
//        deepOcean = colorResource(id = R.color.blue_deep_ocean),
//        skyBlue = colorResource(id = R.color.blue_sky),
//        nightBlue = colorResource(id = R.color.blue_night),
//        dialogBackground = colorResource(id = R.color.dialog_background)
//    )
//
//// night colors extented - cores auxiliares para dia que vão além do padrão
//val darkExtendedColors: ExtendedColors
//    @Composable
//    get() = ExtendedColors(
//        snowWhite = colorResource(id = R.color.white_snow),
//        deepOcean = colorResource(id = R.color.black_inverted_mode),
//        skyBlue = colorResource(id = R.color.blue_sky),
//        nightBlue = colorResource(id = R.color.black_plain),
//        dialogBackground = colorResource(id = R.color.black_inverted_mode)
//    )