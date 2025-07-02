plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 32
    namespace = "com.kevin.gridpager.sample"

    defaultConfig {
        applicationId = "com.kevin.gridpager.sample"
        minSdk = 14
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    dataBinding {
        isEnabled = true
    }

}

dependencies {
    implementation(fileTree(baseDir = "libs") {
        include("*.jar", "*.aar")
    })
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.6.21")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation(project(":gridpagerview"))
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    implementation("com.zwenkai:basedialog:1.1.4")
    implementation("com.zwenkai:wheelview:1.0.3")
//    implementation("com.zwenkai:gridpagerview:2.0.2")
}
