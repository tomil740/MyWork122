plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
    alias(libs.plugins.daggerhiltandroid)
}

android {
    namespace = "com.example.mywork122.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.mywork122.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    implementation(libs.hiltNavigationCompose)
    implementation(libs.kotlinx.datetime)


    ///
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    implementation("androidx.compose.material:material:1.6.1")


}