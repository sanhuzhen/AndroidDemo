plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.sanhuzhen.roomdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sanhuzhen.roomdemo"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {

    // 引入依赖
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    // Room协程支持
    implementation("androidx.room:room-ktx:$room_version")
    // Room RxJava3 支持
    implementation("androidx.room:room-rxjava3:$room_version")

    // 协程
    val coroutine_version = "1.5.0"
    // 协程核心库
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version")
    // 提供Android专属调度器
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}