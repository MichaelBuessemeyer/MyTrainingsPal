package com.example.mytrainingpal.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkPetrol,
    secondary = Orange,
    background = LightPetrol,
)

private val LightColorPalette = lightColors(
    primary = DarkPetrol,
    onPrimary = Color.White,
    background = LightPetrol,
    onBackground = Color.White,
    secondary = Orange,
    onSecondary = Color.Black,

    /*
    secondaryContainer = Color,
    onSecondaryContainer = Color,
    tertiary = Color,
    onTertiary = Color,
    tertiaryContainer = Color,
    onTertiaryContainer = Color,
    background = Color,
    onBackground = Color,
    surface = Color,
    onSurface = Color,
    surfaceVariant = Color,
    onSurfaceVariant = Color,
    surfaceTint = Color,
    inverseSurface = Color,
    inverseOnSurface = Color,
    error = Color,
    onError = Color,
    errorContainer = Color,
    onErrorContainer = Color,
    outline = Color,
    outlineVariant = Color,
    scrim = Color*/

    /* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/
)

@Composable
fun MyTrainingPalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}