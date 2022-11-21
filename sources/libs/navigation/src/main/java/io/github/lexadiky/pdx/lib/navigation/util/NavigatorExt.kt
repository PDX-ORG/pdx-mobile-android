package io.github.lexadiky.pdx.lib.navigation.util

import io.github.lexadiky.pdx.lib.navigation.Navigator
import java.net.URI

suspend fun Navigator.navigate(uri: URI) {
    navigate(uri.toString())
}