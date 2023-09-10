plugins {
  id("com.android.dynamic-feature")
  id("org.jetbrains.kotlin.android")
  id("com.google.dagger.hilt.android")
  id("kotlin-kapt")
}
android {
  namespace = "com.deanuharatinu.moviedatabase.favorite"
  compileSdk = 33

  defaultConfig {
    minSdk = 28
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
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
}

apply(from = "../shared_dependencies.gradle")

dependencies {
  implementation(project(":app"))
  implementation(project(":core"))
}

kapt {
  correctErrorTypes = true
}