package io.github.lexadiky.pdx.feature.news.entity.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class RedditResponse(
    @SerialName("data")
    val data: Data
) {

    @Serializable
    data class Data(
        @SerialName("children")
        val children: List<Child>
    ) {

        @Serializable
        data class Child(
            @SerialName("data")
            val data: Data
        ) {

            @Serializable
            data class Data(
                @SerialName("title")
                val title: String,
                @SerialName("url")
                val url: String,
                @SerialName("author")
                val author: String,
                @SerialName("preview")
                val preview: Preview? = null,
                @SerialName("created_utc")
                val createdTimestamp: Double,
            ) {

                @Serializable
                data class Preview(
                    @SerialName("images")
                    val images: List<Images>
                ) {

                    @Serializable
                    data class Images(
                        @SerialName("resolutions")
                        val resolutions: List<Source>
                    ) {

                        @Serializable
                        data class Source(
                            @SerialName("url")
                            val url: String?
                        )
                    }
                }
            }
        }
    }
}