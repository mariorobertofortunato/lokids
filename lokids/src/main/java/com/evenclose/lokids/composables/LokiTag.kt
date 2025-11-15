package com.evenclose.lokids.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.evenclose.lokids.ui.theme.LokiPaddings
import com.evenclose.lokids.ui.theme.LokiRadiuses

@Composable
fun LokiTag(
    text: String,
    color: Color = MaterialTheme.colorScheme.primary,
){
    LokiTextLabel(
        text = "#$text",
        color = color,
        size = 12.sp,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .background(
                color = color.copy(alpha = 0.25f),
                shape = RoundedCornerShape(LokiRadiuses.small)
            )
            .padding(horizontal = LokiPaddings.medium, vertical = LokiPaddings.small)

    )

}

@Preview(showBackground = true)
@Composable
private fun LokiTagPreview() {
    LokiTag(text = "ExampleTag")
}