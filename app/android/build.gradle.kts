plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlin.multiplatform)
}

android {
    namespace = "com.jcookeak.chatllm.android"
    compileSdk = 34
    
    defaultConfig {
        applicationId = "com.jcookeak.chatllm"
        minSdk = 24
        targetSdk = 34
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
                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.lifecycle.runtime.ktx)
                implementation(libs.androidx.activity.compose)
                implementation(libs.koin.android)
                
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