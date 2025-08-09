# Chat-llm - Kotlin Multiplatform Chat UI

An experimental repository for leveraging Kotlin Multiplatform to build a chat UI for OpenAI compatible APIs.

**ALWAYS follow these instructions first** and fallback to additional search and context gathering only if the information here is incomplete or found to be in error.

## Current Repository State

**CRITICAL**: This repository is currently in an initial state with minimal setup. It contains only basic project files (README, LICENSE, .gitignore) but **NO source code, build scripts, or project structure** has been implemented yet.

## Development Environment Setup

### Prerequisites
Verify these tools are available before starting development:

- **Java JDK**: OpenJDK 17 is required and available at `/usr/bin/java`
  - Verify with: `java -version` (should show 17.x)
- **Gradle**: Version 9.0.0 is available at `/usr/bin/gradle`
  - Verify with: `gradle --version`
- **Kotlin**: Version 2.2.0 is available at `/usr/bin/kotlinc`
  - Verify with: `kotlinc -version`

### Current Working Commands
These commands have been validated to work in the current environment:

```bash
# Environment verification - these work immediately
java -version
gradle --version  
kotlinc -version

# Repository status
git status
git log --oneline
ls -la
```

## Project Initialization (Required for Development)

**IMPORTANT**: Since no Kotlin Multiplatform project structure exists yet, you must initialize it first:

### Option 1: Initialize Standard Kotlin Library
```bash
# Create basic Kotlin library structure
gradle init --type kotlin-library --dsl kotlin --use-defaults --overwrite
```

**IMPORTANT**: Use `--overwrite` flag since the repository contains existing files (README, LICENSE, .gitignore).

**Build Time**: First build takes approximately **5-6 seconds** for basic setup. NEVER CANCEL. Set timeout to 5+ minutes.
**Test Time**: Tests run in approximately **1-2 seconds**. Set timeout to 2+ minutes.

### Option 2: Manual Kotlin Multiplatform Setup
For a proper multiplatform project, create these files manually:

1. **settings.gradle.kts**:
```kotlin
rootProject.name = "Chat-llm"
```

2. **build.gradle.kts** (root):
```kotlin
plugins {
    kotlin("multiplatform") version "2.2.0"
}

kotlin {
    jvm()
    js(IR) {
        browser()
        nodejs()
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Add common dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
```

3. **gradle.properties**:
```
kotlin.code.style=official
```

## Build and Test Commands

**After project initialization**, these commands will be available:

### Building
```bash
# Use Gradle wrapper for consistent builds
./gradlew build
```
**NEVER CANCEL**: First build takes 5-6 seconds. Subsequent builds are faster (~1-2 seconds). Set timeout to 5+ minutes.

### Testing
```bash
./gradlew test
```
**Timing**: Tests complete in 1-2 seconds. Set timeout to 2+ minutes.

### Other Common Tasks
```bash
# Clean build artifacts
./gradlew clean

# Check available tasks
./gradlew tasks

# Run checks without tests
./gradlew check

# Build specific targets (after multiplatform setup)
./gradlew jvmJar
./gradlew jsJar
```

## Validation Requirements

After making any code changes, **ALWAYS** run this validation sequence:

1. **Build verification**: `./gradlew build` (wait for completion - 5-6 seconds)
2. **Test execution**: `./gradlew test` (wait for completion - 1-2 seconds)
3. **Clean build test**: `./gradlew clean build` (first time after changes)

**CRITICAL**: Do not skip validation steps due to timing. Builds may seem slow but are working correctly.

### Manual Testing Scenario

After project initialization, test that the basic functionality works:

1. **Verify sample code**: Check that `lib/src/main/kotlin/org/example/Library.kt` exists
2. **Verify tests pass**: Run `./gradlew test` - should show "1 test completed" 
3. **Test modification**: Edit `Library.kt` to change return value from `true` to `false`
4. **Verify test fails**: Run `./gradlew test` - should show test failure
5. **Revert change**: Change back to `true` and verify tests pass again

This validates that the build and test cycle is working correctly.

## Development Workflow for Chat UI

Once the project structure is initialized:

1. **Common module** (`src/commonMain/kotlin`): Shared chat logic, API interfaces
2. **JVM module** (`src/jvmMain/kotlin`): Desktop/server implementation
3. **JS module** (`src/jsMain/kotlin`): Web browser implementation
4. **Test modules**: Corresponding test directories for each platform

## Project Structure (After Initialization)

```
Chat-llm/
├── .github/
│   └── copilot-instructions.md
├── .gitignore
├── README.md
├── LICENSE
├── build.gradle.kts           # (after init)
├── settings.gradle.kts        # (after init)
├── gradle.properties          # (after init)
├── gradlew                    # (after init)
├── gradlew.bat               # (after init)
├── gradle/                   # (after init)
└── src/                      # (after init)
    ├── commonMain/kotlin/
    ├── commonTest/kotlin/
    ├── jvmMain/kotlin/
    ├── jvmTest/kotlin/
    ├── jsMain/kotlin/
    └── jsTest/kotlin/
```

## Known Limitations

- **No CI/CD**: No GitHub Actions or build automation exists yet
- **No Dependencies**: No package dependencies configured
- **No UI Framework**: No specific UI framework chosen for the chat interface
- **No OpenAI Integration**: API integration code not implemented

## Quick Reference Commands

### Immediate Actions (Work Now)
```bash
git status
ls -la
java -version
gradle --version
```

### Post-Initialization Actions (After gradle init)
```bash
./gradlew build        # 5-6 seconds, NEVER CANCEL, timeout 5+ minutes
./gradlew test         # 1-2 seconds, timeout 2+ minutes  
./gradlew clean build  # Full rebuild verification
./gradlew tasks        # See all available tasks
```

## Troubleshooting

- **"gradlew not found"**: Run `gradle init` first to create project structure
- **Build hangs**: Wait at least 10 minutes before investigating - builds are slow on first run
- **Permission denied on gradlew**: Run `chmod +x gradlew` after initialization
- **Kotlin plugin errors**: Ensure you're using Kotlin 2.2.0 compatible plugins

## Next Development Steps

1. Initialize Kotlin Multiplatform project structure
2. Set up OpenAI API client in common module
3. Implement basic chat UI for each platform (JVM/JS)
4. Add unit tests for API integration
5. Set up CI/CD pipeline
6. Add documentation for chat UI components

**Always build and test your changes** before committing. The build system is working correctly despite apparent slowness.