package com.jcookeak.chatllm.client

import com.jcookeak.chatllm.core.logging.Logger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * HTTP client for OpenAI compatible APIs
 */
class ChatApiClient(
    private val baseUrl: String,
    private val apiKey: String
) {
    private val logger = Logger.create<ChatApiClient>()
    
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        
        install(Logging) {
            level = LogLevel.INFO
        }
    }
    
    suspend fun sendChatMessage(message: String): Flow<ChatResponse> = flow {
        logger.info("Sending chat message: $message")
        
        val request = ChatRequest(
            model = "gpt-3.5-turbo",
            messages = listOf(
                ChatMessage(role = "user", content = message)
            ),
            stream = false
        )
        
        try {
            val response: ChatResponse = httpClient.post("$baseUrl/chat/completions") {
                header("Authorization", "Bearer $apiKey")
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
            
            logger.info("Received response with ${response.choices.size} choices")
            emit(response)
        } catch (e: Exception) {
            logger.error("Failed to send chat message", e)
            throw e
        }
    }
    
    fun close() {
        httpClient.close()
    }
}

@Serializable
data class ChatRequest(
    val model: String,
    val messages: List<ChatMessage>,
    val stream: Boolean = false,
    val temperature: Double = 0.7
)

@Serializable
data class ChatMessage(
    val role: String,
    val content: String
)

@Serializable
data class ChatResponse(
    val id: String,
    val created: Long,
    val model: String,
    val choices: List<ChatChoice>
)

@Serializable
data class ChatChoice(
    val index: Int,
    val message: ChatMessage,
    val finishReason: String? = null
)