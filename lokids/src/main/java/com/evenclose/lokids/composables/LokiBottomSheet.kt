package com.evenclose.lokids.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.evenclose.lokids.ui.theme.LokiPaddings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LokiBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    title: String? = null,
    isCentered: Boolean = false,
    content: @Composable () -> Unit,
) {

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(LokiPaddings.large),
            horizontalAlignment = if (isCentered) Alignment.CenterHorizontally else Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LokiPaddings.extraLarge)
                .padding(bottom = LokiPaddings.extraLarge)
                .verticalScroll(
                    state = rememberScrollState(),
                )
        ) {
            if (label != null || title != null) {
                LokiBottomSheetHeader(label = label, title = title, centerAligned = isCentered)
            }
            content()
        }
    }
}

@Composable
private fun LokiBottomSheetHeader(
    modifier: Modifier = Modifier,
    label: String? = null,
    title: String? = null,
    centerAligned: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(bottom = LokiPaddings.large),
        horizontalAlignment = if (centerAligned) Alignment.CenterHorizontally else Alignment.Start,
    ) {
        if (label != null) {
            Text(
                text = label.uppercase(),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.75f),
                style = MaterialTheme.typography.labelMedium,
            )
        }
        if (title != null) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Black
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun LokiBottomSheetPreview() {
    LokiBottomSheet(
        sheetState = rememberModalBottomSheetState(true),
        onDismiss = { },
        label = "Example Label",
        title = "Example Title"
    ) {
        LokiTextLabel(text = "This is a bottom sheet.")
        LokiTextLabel(text = "You can put any content here.")
        LokiTextInput(text = "Example value", onClick = {})
    }
}
