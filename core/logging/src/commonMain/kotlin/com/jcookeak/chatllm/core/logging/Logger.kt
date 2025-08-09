package com.jcookeak.chatllm.core.logging

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/**
 * Simple multiplatform logger for the Chat-llm application
 */
class Logger(private val tag: String) {
    
    fun debug(message: String) {
        log(LogLevel.DEBUG, message)
    }
    
    fun info(message: String) {
        log(LogLevel.INFO, message)
    }
    
    fun warn(message: String) {
        log(LogLevel.WARN, message)
    }
    
    fun error(message: String, throwable: Throwable? = null) {
        log(LogLevel.ERROR, message, throwable)
    }
    
    private fun log(level: LogLevel, message: String, throwable: Throwable? = null) {
        val timestamp = Clock.System.now()
        val logMessage = formatLogMessage(timestamp, level, tag, message)
        
        // Platform-specific logging will be implemented in actual implementations
        println(logMessage)
        
        throwable?.let {
            println("Exception: ${it.message}")
            // Stack trace would be printed here in platform-specific manner
        }
    }
    
    private fun formatLogMessage(
        timestamp: Instant,
        level: LogLevel,
        tag: String,
        message: String
    ): String {
        return "${timestamp} [${level.name}] $tag: $message"
    }
    
    companion object {
        fun create(tag: String): Logger = Logger(tag)
        
        inline fun <reified T> create(): Logger = Logger(T::class.simpleName ?: "Unknown")
    }
}

enum class LogLevel {
    DEBUG, INFO, WARN, ERROR
}