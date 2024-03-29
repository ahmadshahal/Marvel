package com.kotlinhero.marvel.main.ui.theme

import androidx.compose.material3.Typography

private val defaultTypography = Typography()
private val defaultFontFamily = Gilroy

val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = defaultFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = defaultFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = defaultFontFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = defaultFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = defaultFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = defaultFontFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = defaultFontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = defaultFontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = defaultFontFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = defaultFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = defaultFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = defaultFontFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = defaultFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = defaultFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = defaultFontFamily)
)