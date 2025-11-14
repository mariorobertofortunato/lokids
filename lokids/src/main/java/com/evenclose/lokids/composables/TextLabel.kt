package com.evenclose.lokids.composables

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp

@Composable
fun TextLabel(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    size: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
    textAlign: TextAlign? = TextAlign.Center,
    letterSpacing: Float = 1f
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = size,
        fontWeight = fontWeight,
        textAlign = textAlign,
        letterSpacing = TextUnit(letterSpacing, TextUnitType.Sp)
    )
}

@Composable
fun TextLabel(
    text: String,
    modifier: Modifier = Modifier,
    brush: Brush = Brush.horizontalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.onBackground,
            MaterialTheme.colorScheme.tertiary,
            MaterialTheme.colorScheme.onTertiaryContainer,
        )
    ),
    size: TextUnit = 20.sp,
    textAlign: TextAlign? = TextAlign.Center,
    letterSpacing: Float = 1f
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = size,
        style = LocalTextStyle.current.merge(
            TextStyle(
                brush = brush,
                fontSize = size,
                fontWeight = FontWeight.SemiBold,
                textAlign = textAlign ?: TextAlign.Center,
                letterSpacing = TextUnit(letterSpacing, TextUnitType.Sp)
            )
        )
    )
}