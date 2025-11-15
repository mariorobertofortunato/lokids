package com.evenclose.lokids.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.evenclose.lokids.ui.theme.LokiElevations
import com.evenclose.lokids.ui.theme.LokiPaddings
import com.evenclose.lokids.ui.theme.LokiRadiuses
import com.evenclose.lokids.ui.theme.LokiSizes

@Composable
fun LokiCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Info,
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
        CardContent(title, icon, description)
    }
}

@Composable
fun LokiAnimatedCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Info,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "Card-Border-Animation")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing)
        ),
        label = "Card-Border-Animation-Angle"
    )

    val brush = Brush.sweepGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.tertiary,
        )
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(LokiRadiuses.small),
        elevation = CardDefaults.cardElevation(
            defaultElevation = LokiElevations.small
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(LokiRadiuses.small))
                .drawWithContent {
                    rotate(angle) {
                        drawCircle(
                            brush = brush,
                            radius = size.width,
                            blendMode = BlendMode.SrcIn,
                        )
                    }
                    drawContent()
                }
                .padding(LokiSizes.animatedBorderWidth)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(LokiRadiuses.small),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                CardContent(title, icon, description)
            }
        }
    }
}

@Composable
private fun CardContent(title: String, icon: ImageVector, description: String) {
    Row(
        modifier = Modifier.padding(LokiPaddings.large),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LokiPaddings.large)
    ) {
        Icon(
            imageVector = icon,
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
private fun LokiAnimatedCardPreview() {
    LokiAnimatedCard(
        title = "Animated Card Title",
        description = "This card has a cool animated border.",
        modifier = Modifier.padding(LokiPaddings.large)
    )
}
