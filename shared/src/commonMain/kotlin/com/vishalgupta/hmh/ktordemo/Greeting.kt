package com.vishalgupta.hmh.ktordemo

import com.vishalgupta.hmh.ktordemo.networking.NetworkingService
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.Serializable

@Serializable
data class Hello (
    val string:String
)

class Greeting {
    val networkingService = NetworkingService()
    suspend fun greeting(): String {
        return "Hello, ${Platform().platform}! ${networkingService.getSinglePost().title}"
    }
}