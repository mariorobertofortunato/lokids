package com.evenclose.lokids.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.evenclose.lokids.ui.theme.LokiIconSizes
import com.evenclose.lokids.ui.theme.LokiPaddings
import com.evenclose.lokids.ui.theme.LokiRadiuses

@Composable
fun LokiPopup(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)? = null,
    onConfirm: (() -> Unit)? = null,
    confirmButtonText: String = "OK",
    showCloseIcon: Boolean = true,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = LokiPaddings.extraLarge)
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showCloseIcon) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = LokiPaddings.large)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Popup Dismiss Icon",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(LokiIconSizes.medium)
                            .clickable(onClick = onDismissRequest)
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(LokiRadiuses.large),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(LokiPaddings.large)) {
                    title?.invoke()

                    content()

                    if (onConfirm != null) {
                        Button(
                            onClick = onConfirm,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = LokiPaddings.large)
                        ) {
                            LokiTextLabel(
                                text = confirmButtonText
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LokiPopupPreview() {
    LokiPopup(
        onDismissRequest = {},
        onConfirm = {},
        title = {
            LokiTextLabel(
                text = "This is a Title",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = LokiPaddings.large)
            )
        },
        content = {
            LokiTextLabel(text = "This is the content of the popup.")
        }
    )
}
