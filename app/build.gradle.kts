@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
   id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.picpeek"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.picpeek"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding =true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.converter)
    implementation(libs.gson)
    implementation(libs.glide)
    implementation(libs.navigation)
    implementation(libs.navigationUi)
    implementation(libs.viewModel)
    implementation(libs.viewModelEx)
    implementation(libs.coroutinesAndroid)
    implementation(libs.coroutinesCore)
    implementation(libs.okhttp)
    implementation(libs.interceptor)
    implementation(libs.lottie)
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)

}
kapt {
    correctErrorTypes = true
}