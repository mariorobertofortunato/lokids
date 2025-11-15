package com.evenclose.lokids.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.evenclose.lokids.ui.theme.LokiPaddings
import kotlinx.coroutines.launch

private enum class LokiCollapsibleSlot {
    Header,
    Body
}

@Composable
fun LokiCollapsibleScaffold(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    body: @Composable (PaddingValues) -> Unit,
) {
    var headerHeight by remember { mutableFloatStateOf(0f) }
    val headerOffset = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                coroutineScope.launch {
                    headerOffset.snapTo( (headerOffset.value + delta).coerceIn(-headerHeight, 0f) )
                }
                return Offset.Zero
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                if (headerOffset.value < 0 && headerOffset.value > -headerHeight) {
                    coroutineScope.launch {
                        if (headerOffset.value < -headerHeight / 2) {
                            headerOffset.animateTo(-headerHeight, animationSpec = tween(400))
                        } else {
                            headerOffset.animateTo(0f, animationSpec = tween(400))
                        }
                    }
                }
                return super.onPostFling(consumed, available)
            }
        }
    }

    SubcomposeLayout(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) { constraints ->
        val headerContent = @Composable { header() }
        val headerMeasurable = subcompose(LokiCollapsibleSlot.Header, headerContent).first()
        val headerPlaceable = headerMeasurable.measure(constraints)
        headerHeight = headerPlaceable.height.toFloat()

        val bodyContent = @Composable {
            body(PaddingValues(top = (headerHeight + headerOffset.value).toDp()))
        }
        val bodyPlaceable = subcompose(LokiCollapsibleSlot.Body, bodyContent).first().measure(constraints)

        layout(constraints.maxWidth, constraints.maxHeight) {
            bodyPlaceable.placeRelative(0, 0)
            headerPlaceable.placeRelative(0, headerOffset.value.toInt())
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LokiCollapsibleScaffoldPreview() {
    LokiCollapsibleScaffold(
        header = {
            LokiCard(
                title = "Collapsible Header",
                description = "This header will collapse when scrolling up.",
                modifier = Modifier.padding(
                    horizontal = LokiPaddings.large,
                    vertical = LokiPaddings.medium
                )
            )
        },
        body = { paddingValues ->
            LazyColumn(contentPadding = paddingValues) {
                items(20) {
                    LokiTextInput(
                        text = "Scrollable Item #$it",
                        onClick = {},
                        modifier = Modifier.padding(
                            horizontal = LokiPaddings.large,
                            vertical = LokiPaddings.medium
                        )
                    )
                }
            }
        }
    )
}
