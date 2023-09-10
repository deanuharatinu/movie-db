plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("com.google.dagger.hilt.android")
  id("kotlin-kapt")
}

android {
  namespace = "com.deanuharatinu.core"
  compileSdk = 33

  defaultConfig {
    minSdk = 28

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")

    buildConfigField("String", "API_KEY", "\"b650046bf640e7bf7054093854b8d02a\"")
    buildConfigField("String", "CHIPER_KEY", "\"b650046bf640e7bf7054093854b8d02a\"")
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
}

apply(from = "../shared_dependencies.gradle")

dependencies {
  // Retrofit
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
  implementation("com.squareup.moshi:moshi:1.14.0")
  implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
  implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

  // Room
  implementation("androidx.room:room-runtime:2.5.2")
  kapt("androidx.room:room-compiler:2.5.2")
  implementation("androidx.room:room-ktx:2.5.2")
  androidTestImplementation("androidx.room:room-testing:2.5.2")

  implementation("net.zetetic:android-database-sqlcipher:4.5.3")
  implementation("androidx.sqlite:sqlite-ktx:2.3.1")
}

kapt {
  correctErrorTypes = true
}