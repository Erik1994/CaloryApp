plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

apply {
    from("$rootDir/base-module.gradle")
}

android {
    namespace = ProjectConfig.coreNameSpace
}

dependencies { }