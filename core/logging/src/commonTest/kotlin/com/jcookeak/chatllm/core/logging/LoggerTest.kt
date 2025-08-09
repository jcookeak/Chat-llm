package com.jcookeak.chatllm.core.logging

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class LoggerTest {
    
    @Test
    fun testLoggerCreation() {
        val logger = Logger.create("TestTag")
        assertNotNull(logger)
    }
    
    @Test
    fun testLoggerCreateWithClass() {
        val logger = Logger.create<LoggerTest>()
        assertNotNull(logger)
    }
    
    @Test
    fun testLogLevels() {
        val logger = Logger.create("TestTag")
        
        // These should not throw exceptions
        logger.debug("Debug message")
        logger.info("Info message")
        logger.warn("Warning message")
        logger.error("Error message")
    }
    
    @Test
    fun testLogLevelEnum() {
        assertEquals("DEBUG", LogLevel.DEBUG.name)
        assertEquals("INFO", LogLevel.INFO.name)
        assertEquals("WARN", LogLevel.WARN.name)
        assertEquals("ERROR", LogLevel.ERROR.name)
    }
}