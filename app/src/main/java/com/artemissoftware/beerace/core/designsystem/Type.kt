package com.artemissoftware.beerace.core.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.artemissoftware.beerace.R



private val aBeeZeeFamily: FontFamily = FontFamily(
    listOf(
        Font(R.font.abeezee_regular),
    ),
)

private val undefined = TextStyle()

private val defaultTypography = Typography()

val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = aBeeZeeFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = aBeeZeeFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = aBeeZeeFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = aBeeZeeFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = aBeeZeeFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = aBeeZeeFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = aBeeZeeFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = aBeeZeeFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = aBeeZeeFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = aBeeZeeFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = aBeeZeeFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = aBeeZeeFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = aBeeZeeFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = aBeeZeeFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = aBeeZeeFamily)
)