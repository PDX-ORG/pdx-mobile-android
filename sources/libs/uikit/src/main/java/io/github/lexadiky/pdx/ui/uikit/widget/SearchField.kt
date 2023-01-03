@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.ui.uikit.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.pdx.lib.uikit.R

@Composable
fun SearchField(text: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = text,
        placeholder = { Text(stringResource(id = R.string.uikit_search_field_placeholder)) },
        label = { Text(stringResource(id = R.string.uikit_search_field_label)) },
        trailingIcon = { Icon(Icons.Default.Search, null) },
        onValueChange = onTextChanged,
        modifier = modifier
    )
}
