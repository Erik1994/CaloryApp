plugins {
    id("com.android.library")
    kotlin("android")
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = ProjectConfig.trackerDataNameSpace
}

dependencies {
    singleModule(Modules.core)
    singleModule(Modules.trackerDomain)

    retrofit()
    room()
}