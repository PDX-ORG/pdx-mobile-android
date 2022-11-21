package io.github.lexadiky.pdx.ui.uikit.resources

import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.pdx.lib.resources.string.LiteralStringResource
import io.github.lexadiky.pdx.lib.resources.string.ResStringResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.TemporalStringResource
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toLocalDateTime
import java.util.Date

@Composable
fun StringResource.render(): State<String> {
    return when (this) {
        is LiteralStringResource -> remember(this.value) { mutableStateOf(this.value) }
        is ResStringResource -> {
            val text = stringResource(id = this.stringRes)
            remember(this.stringRes, text) { mutableStateOf(text) }
        }

        is TemporalStringResource -> {
            val context = LocalContext.current
            return remember(this.instant) {
                val formatted = DateFormat.getDateFormat(context)
                    .format(Date(instant.epochSeconds * 1000))
                mutableStateOf(formatted)
            }
        }
    }
}