package com.evenclose.lokids.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.evenclose.lokids.ui.theme.LokiIconSizes
import com.evenclose.lokids.ui.theme.LokiPaddings
import com.evenclose.lokids.ui.theme.LokiRadiuses
import com.evenclose.lokids.ui.theme.LokiSizes

@Composable
fun LokiTextInput(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Edit,
    contentDescription: String? = "Edit"
) {
    val shape = RoundedCornerShape(LokiRadiuses.small)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = LokiSizes.borderWidth,
                color = MaterialTheme.colorScheme.outline,
                shape = shape
            )
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(LokiPaddings.large)
        ) {
            LokiTextLabel(
                text = text,
                size = 18.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(LokiIconSizes.small)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LokiTextInputPreview() {
    LokiTextInput(
        text = "Example Value",
        onClick = {}
    )
}
