plugins {
    id("com.android.library")
    kotlin("android")
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = ProjectConfig.trackerPresentationNameSpace
}

dependencies {
    singleModule(Modules.core)
    singleModule(Modules.coreUi)
    singleModule(Modules.trackerDomain)

    coilCompose()
}