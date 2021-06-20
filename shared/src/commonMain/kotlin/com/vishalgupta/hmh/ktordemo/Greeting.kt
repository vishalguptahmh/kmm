package com.vishalgupta.hmh.ktordemo

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
    val httpclient = HttpClient(){
        install(Logging){
            level = LogLevel.ALL
            logger = object : Logger{
                override fun log(message: String) {
                    Napier.v(tag="Http Client",message= message)
                }
            }
        }
        install(JsonFeature){
            val jsonn = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(jsonn)
        }
    }.also { initLogger() }


    @Throws(Exception::class)
    suspend fun greeting(): String {
        return "Hello, ${Platform().platform}! ${getHello()}"
    }

    private suspend fun getHello() : String{
       val response : HttpResponse =  httpclient.get("https://jsonplaceholder.typicode.com/posts/1")
        return response.readText()
    }
}