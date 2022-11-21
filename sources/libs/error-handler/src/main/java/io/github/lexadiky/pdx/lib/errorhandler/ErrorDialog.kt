package io.github.lexadiky.pdx.lib.errorhandler

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import io.github.lexadiky.pdx.ui.uikit.resources.render

@Composable
fun ErrorDialog(error: UIError?, onDismiss: () -> Unit) {
    if (error != null) {
        AlertDialog(
            title = {
                Text(text = stringResource(id = R.string.error_handler_dialog_title))
            },
            text = {
                Text(text = error.message.render().value)
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(id = R.string.error_handler_dialog_dismiss))
                }
            },
            confirmButton = {},
            onDismissRequest = onDismiss
        )
    }
}