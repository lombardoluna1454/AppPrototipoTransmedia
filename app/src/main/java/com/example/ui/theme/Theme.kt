package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = DeepBlue,
    onPrimary = Color.White,
    primaryContainer = LavenderBlue,
    onPrimaryContainer = NightBlue,
    secondary = CoralOrange,
    onSecondary = Color.White,
    secondaryContainer = SoftOrangeBg,
    onSecondaryContainer = NightBlue,
    tertiary = LimeGreen,
    onTertiary = NightBlue,
    tertiaryContainer = SoftGreenBg,
    onTertiaryContainer = NightBlue,
    background = Cream,
    onBackground = NightBlue,
    surface = SurfaceWhite,
    onSurface = NightBlue,
    surfaceVariant = SoftBlueBg,
    onSurfaceVariant = NightBlue,
    outline = SubtleBorder
)

@Composable
fun TcaEducationalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // We intentionally maintain the warm cream and deep blue palette for consistent educational branding
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography,
        content = content
    )
}
