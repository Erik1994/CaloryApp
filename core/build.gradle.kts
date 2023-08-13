plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = ProjectConfig.coreNameSpace
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    "implementation"(DaggerHilt.hiltAndroid)
    "kapt"(DaggerHilt.hiltCompiler)

    "testImplementation"(Testing.junit4)
    "testImplementation"(Testing.junitAndroidExt)
    "testImplementation"(Testing.truth)
    "testImplementation"(Testing.coroutines)
    "testImplementation"(Testing.turbine)
    "testImplementation"(Testing.composeUiTest)
    "testImplementation"(Testing.mockk)
    "testImplementation"(Testing.mockWebServer)

    "androidTestImplementation"(Testing.junit4)
    "androidTestImplementation"(Testing.junitAndroidExt)
    "androidTestImplementation"(Testing.truth)
    "androidTestImplementation"(Testing.coroutines)
    "androidTestImplementation"(Testing.turbine)
    "androidTestImplementation"(Testing.composeUiTest)
    "androidTestImplementation"(Testing.mockkAndroid)
    "androidTestImplementation"(Testing.mockWebServer)
    "androidTestImplementation"(Testing.hiltTesting)
    "kaptAndroidTest"(DaggerHilt.hiltCompiler)
    "androidTestImplementation"(Testing.testRunner)
}