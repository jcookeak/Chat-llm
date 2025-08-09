# Chat-llm

An experimental repo leveraging Kotlin multiplatform to build a chat UI for OpenAI compatible APIs.

## Project Structure

This project uses **Gradle 9.0.0** with Kotlin Multiplatform and is organized into the following modules:

### Core Modules ✅

- **`core/logging`** - Multiplatform logging utilities
- **`client`** - HTTP client for OpenAI compatible APIs using Ktor

### App Modules (To be added)

- **`app/android`** - Android application
- **`app/ios`** - iOS application

## Getting Started

### Prerequisites

- JDK 17 or higher
- Gradle 9.0.0 (included via wrapper)

### Building the Project

```bash
# Build all modules
./gradlew build

# Compile JVM target
./gradlew compileKotlinJvm

# Run tests
./gradlew test

# List all projects
./gradlew projects
```

### Module Structure

#### core/logging
A multiplatform logging library that provides a simple `Logger` class with support for different log levels.

```kotlin
val logger = Logger.create("MyTag")
logger.info("Hello from multiplatform!")
```

#### client
An HTTP client module using Ktor for communicating with OpenAI compatible APIs.

```kotlin
val client = ChatApiClient(
    baseUrl = "https://api.openai.com/v1",
    apiKey = "your-api-key"
)
```

## Development Setup

### Adding New Modules

1. Create the module directory structure
2. Add the module to `settings.gradle.kts`
3. Create `build.gradle.kts` for the module
4. Configure dependencies in `gradle/libs.versions.toml`

### Version Catalog

Dependencies are managed using Gradle version catalogs in `gradle/libs.versions.toml`. This provides centralized dependency management across all modules.

## Target Platforms

- **JVM** ✅
- **Linux/Native** ✅  
- **iOS** (configured, apps pending)
- **Android** (pending proper AGP setup)

## Next Steps

1. Add Android application module (requires compatible Android Gradle Plugin)
2. Add iOS application module with Compose Multiplatform
3. Implement UI components
4. Add chat functionality
