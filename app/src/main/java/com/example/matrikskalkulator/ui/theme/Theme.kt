package com.example.matrikskalkulator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val md_theme_light_primary = Color(0xFF0061A6)
private val md_theme_light_onPrimary = Color(0xFFFFFFFF)
private val md_theme_light_primaryContainer = Color(0xFFD2E4FF)
private val md_theme_light_onPrimaryContainer = Color(0xFF001C37)
private val md_theme_light_secondary = Color(0xFF535F70)
private val md_theme_light_onSecondary = Color(0xFFFFFFFF)
private val md_theme_light_secondaryContainer = Color(0xFFD7E3F8)
private val md_theme_light_onSecondaryContainer = Color(0xFF101C2B)
private val md_theme_light_tertiary = Color(0xFF6B5778)
private val md_theme_light_onTertiary = Color(0xFFFFFFFF)
private val md_theme_light_tertiaryContainer = Color(0xFFF3DAFF)
private val md_theme_light_onTertiaryContainer = Color(0xFF251431)
private val md_theme_light_surface = Color(0xFFFDFCFF)
private val md_theme_light_onSurface = Color(0xFF1A1C1E)

private val md_theme_dark_primary = Color(0xFFA0C9FF)
private val md_theme_dark_onPrimary = Color(0xFF00325A)
private val md_theme_dark_primaryContainer = Color(0xFF004880)
private val md_theme_dark_onPrimaryContainer = Color(0xFFD2E4FF)
private val md_theme_dark_secondary = Color(0xFFBBC7DB)
private val md_theme_dark_onSecondary = Color(0xFF253141)
private val md_theme_dark_secondaryContainer = Color(0xFF3C4858)
private val md_theme_dark_onSecondaryContainer = Color(0xFFD7E3F8)
private val md_theme_dark_tertiary = Color(0xFFD7BEE4)
private val md_theme_dark_onTertiary = Color(0xFF3B2947)
private val md_theme_dark_tertiaryContainer = Color(0xFF523F5F)
private val md_theme_dark_onTertiaryContainer = Color(0xFFF3DAFF)
private val md_theme_dark_surface = Color(0xFF1A1C1E)
private val md_theme_dark_onSurface = Color(0xFFE3E2E6)

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
)

private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
)

private val AppTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

@Composable
fun MatriksKalkulatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> LightColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}