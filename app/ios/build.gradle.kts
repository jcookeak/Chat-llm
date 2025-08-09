plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.compose.compiler)
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.compose.runtime)
                implementation(libs.compose.foundation)
                implementation(libs.compose.material3)
                implementation(libs.compose.ui)
                implementation(libs.compose.components.resources)
                implementation(libs.compose.components.uiToolingPreview)
                implementation(libs.koin.core)
                
                implementation(project(":client"))
                implementation(project(":core:logging"))
            }
        }
        
        val iosMain by creating {
            dependsOn(commonMain.get())
        }
        
        val iosX64Main by getting {
            dependsOn(iosMain)
        }
        
        val iosArm64Main by getting {
            dependsOn(iosMain)
        }
        
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
    }
}