package com.kotlinhero.marvel.common.utils

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import io.ktor.client.plugins.plugin
import io.ktor.client.request.prepareGet
import io.ktor.http.contentLength
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.isEmpty
import io.ktor.utils.io.core.readBytes
import java.io.File

fun HttpClient.clearBearerToken() = plugin(Auth)
    .providers
    .filterIsInstance<BearerAuthProvider>()
    .firstOrNull()?.clearToken()

suspend fun HttpClient.download(
    url: String,
    file: File,
    onDownloadProgressChange: ((Float) -> Unit)? = null
) {
    file.writeBytes(ByteArray(0))
    prepareGet(url).execute { httpResponse ->
        val channel: ByteReadChannel = httpResponse.body()
        while (!channel.isClosedForRead) {
            val packet = channel.readRemaining(DEFAULT_BUFFER_SIZE.toLong())
            while (!packet.isEmpty) {
                val bytes = packet.readBytes()
                file.appendBytes(bytes)
                onDownloadProgressChange?.invoke(
                    file.length() / (httpResponse.contentLength() ?: 1024).toFloat()
                )
            }
        }
    }
}
