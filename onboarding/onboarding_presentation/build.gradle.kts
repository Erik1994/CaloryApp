plugins {
    id("com.android.library")
    kotlin("android")
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = ProjectConfig.onBoardingPresentationNameSpace
}

dependencies {
    singleModule(Modules.core)
    singleModule(Modules.coreUi)
    singleModule(Modules.onboardingDomain)
}