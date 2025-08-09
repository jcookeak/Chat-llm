plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.compose.compiler)
}

android {
    namespace = "com.jcookeak.chatllm.android"
    compileSdk = 35
    
    defaultConfig {
        applicationId = "com.jcookeak.chatllm"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    buildFeatures {
        compose = true
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    
    sourceSets {
        androidMain {
            dependencies {
                implementation(platform(libs.compose.bom))
                implementation(libs.compose.ui)
                implementation(libs.compose.ui.tooling.preview)
                implementation(libs.compose.material3)
                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.lifecycle.runtime.ktx)
                implementation(libs.androidx.activity.compose)
                implementation(libs.koin.android)
                implementation(libs.koin.compose)
                
                implementation(project(":client"))
                implementation(project(":core:logging"))
            }
        }
        
        androidUnitTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}