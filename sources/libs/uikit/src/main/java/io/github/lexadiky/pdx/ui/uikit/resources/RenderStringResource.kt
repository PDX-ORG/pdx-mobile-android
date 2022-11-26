package io.github.lexadiky.pdx.ui.uikit.resources

import android.content.Context
import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.pdx.lib.resources.string.FormattedStringResource
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

        is FormattedStringResource -> {
            val renderedBase by this.base.render()
            remember {
                derivedStateOf { renderedBase.format(*this.arguments) }
            }
        }
    }
}

fun StringResource.render(context: Context): String {
    return when (this) {
        is FormattedStringResource -> this.base.render(context)
            .format(*this.arguments)
        is LiteralStringResource -> this.value
        is ResStringResource -> context.getString(this.stringRes)
        is TemporalStringResource -> DateFormat.getDateFormat(context)
            .format(Date(instant.epochSeconds * 1000))

    }
}