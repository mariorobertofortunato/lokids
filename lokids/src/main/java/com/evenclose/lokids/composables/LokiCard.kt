package com.evenclose.lokids.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.evenclose.lokids.ui.theme.LokiElevations
import com.evenclose.lokids.ui.theme.LokiPaddings
import com.evenclose.lokids.ui.theme.LokiRadiuses

@Composable
fun LokiCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(LokiRadiuses.small),
        elevation = CardDefaults.cardElevation(
            defaultElevation = LokiElevations.small
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        content()
    }
}

@Composable
fun LokiCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Info,
) {
    LokiCard(modifier = modifier) {
        CardContent(title, rememberVectorPainter(image = icon), description)
    }
}

@Composable
fun LokiCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
) {
    LokiCard(modifier = modifier) {
        CardContent(title, painterResource(id = icon), description)
    }
}

@Composable
internal fun CardContent(title: String, icon: Painter, description: String) {
    Row(
        modifier = Modifier.padding(LokiPaddings.large),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LokiPaddings.large)
    ) {
        Icon(
            painter = icon,
            contentDescription = title, // Better for accessibility
            tint = MaterialTheme.colorScheme.primary
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(LokiPaddings.small),
            horizontalAlignment = Alignment.Start
        ) {
            LokiTextLabel(
                text = title,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Start,
                size = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            LokiTextLabel(
                text = description,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal,
                size = MaterialTheme.typography.bodyMedium.fontSize,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LokiCardPreview() {
    LokiCard(
        title = "Example Title",
        description = "This is an example description for the LokiCard component.",
        modifier = Modifier.padding(LokiPaddings.large)
    )
}

@Preview(showBackground = true)
@Composable
private fun LokiCardCustomContentPreview() {
    LokiCard(
        modifier = Modifier.padding(LokiPaddings.large)
    ) {
        Row(
            modifier = Modifier.padding(LokiPaddings.large),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(LokiPaddings.large)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
            Column {
                LokiTextLabel(
                    text = "Custom Title",
                    fontWeight = FontWeight.Bold
                )
                LokiTextLabel(text = "This is a fully custom composable inside a LokiCard.")
            }
        }
    }
}
