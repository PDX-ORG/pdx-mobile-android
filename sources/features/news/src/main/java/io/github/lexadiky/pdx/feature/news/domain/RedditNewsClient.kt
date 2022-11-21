package io.github.lexadiky.pdx.feature.news.domain

import io.github.lexadiky.pdx.feature.news.entity.domain.RedditResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class RedditNewsClient(private val httpClient: HttpClient) {

    suspend fun load(): RedditResponse {
        return httpClient.get("https://www.reddit.com/r/PokeLeaks/.json")
            .body()
    }
}