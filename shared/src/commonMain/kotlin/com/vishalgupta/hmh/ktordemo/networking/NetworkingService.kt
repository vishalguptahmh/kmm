package com.vishalgupta.hmh.ktordemo.networking

import com.vishalgupta.hmh.ktordemo.initLogger
import com.vishalgupta.hmh.ktordemo.listModule.ModelPost
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class NetworkingService {

   private val httpclient = HttpClient(){
        install(Logging){
            level = LogLevel.ALL
            logger = object : Logger {
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
    public suspend fun getHello() : String{
        val response : HttpResponse =  httpclient.get("https://jsonplaceholder.typicode.com/posts/1")
        return response.readText()
    }

    @Throws(Exception::class)
    public suspend fun getSinglePost() : ModelPost{
        val response : ModelPost =  httpclient.get("https://jsonplaceholder.typicode.com/posts/1")
        return response;
    }

    @Throws(Exception::class)
    public suspend fun getPostList():List<ModelPost>{
        val response : List<ModelPost> = httpclient.get("https://jsonplaceholder.typicode.com/posts")
        return  response;
    }
}