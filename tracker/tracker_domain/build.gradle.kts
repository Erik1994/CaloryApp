plugins {
    id("com.android.library")
    kotlin("android")
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = ProjectConfig.trackerDoaminNameSpace
}

dependencies {
    singleModule(Modules.core)
}