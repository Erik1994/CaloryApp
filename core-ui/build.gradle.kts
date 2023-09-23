plugins {
    id("com.android.library")
    kotlin("android")
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = ProjectConfig.coreUiNameSpace
}

dependencies { }