

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.22"
    alias(libs.plugins.sqlDelightGradle)

}

kotlin {

    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            //w isStatic = false

        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.datetime)
                implementation(libs.androidXCoroutines)
                implementation(libs.sqlDelightRuntime)
                implementation(libs.sqlDelightCoroutinesExtensions)

            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.sqlDelightAndroidDriver)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

       // val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.5.4")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

}

android {
    namespace = "com.example.mywork122"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}


sqldelight {
    database("WorkDatabase") {
        packageName = "com.example.mywork122.database"
        sourceFolders = listOf("sqldelight")
    }
}















