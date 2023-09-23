plugins {
    id("com.android.library")
    kotlin("android")
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = ProjectConfig.onBoardingPresentationNameSpace
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.onboardingDomain))
}