@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
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
        buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")

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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        dataBinding = true
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
    implementation(libs.viewModelEx)
    implementation(libs.viewModelLifecycle)
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