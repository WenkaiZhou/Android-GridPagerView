
plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 30
    namespace = "com.kevin.gridpager"

    defaultConfig {
        minSdk = 14
        targetSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.21")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.viewpager:viewpager:1.0.0")
//    compileOnly("androidx.databinding:databinding-runtime:8.5.1")
    compileOnly("androidx.databinding:databinding-runtime:4.2.1")
}

// Release maven configuration

apply(plugin = "com.vanniktech.maven.publish")