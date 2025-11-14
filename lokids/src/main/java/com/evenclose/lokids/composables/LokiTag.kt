package com.evenclose.lokids.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LokiTag(
    text: String,
    color: Color = Color.Red,
){
    TextLabel(
        text = "#$text",
        color = MaterialTheme.colorScheme.primary,
        size = 12.sp,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .background(
                color = color.copy(alpha = 0.25f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)

    )

}