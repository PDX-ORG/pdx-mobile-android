package io.github.lexadiky.pdx.lib.network

import android.content.Context
import io.github.lexadiky.pdx.lib.arc.di.module
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import okhttp3.Cache
import java.io.File

val NetworkModule by module {
    single {
        val context: Context = inject()
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }
            engine {
                config {
                    cache(
                        Cache(
                            directory = File(context.cacheDir, "http_cache"),
                            maxSize = 50L * 1024L * 1024L // 50 MiB
                        )
                    )
                }
            }
        }
    }
}