plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("com.google.dagger.hilt.android")
  id("kotlin-kapt")
}

android {
  namespace = "com.deanuharatinu.moviedatabase"
  compileSdk = 33

  defaultConfig {
    applicationId = "com.deanuharatinu.moviedatabase"
    minSdk = 28
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
    debug {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    viewBinding = true
    buildConfig = true
  }
  dynamicFeatures += setOf(":favorite")
}

apply(from = "../shared_dependencies.gradle")

dependencies {
  implementation(project(":core"))

  // Leak canary
  debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")
}

kapt {
  correctErrorTypes = true
}