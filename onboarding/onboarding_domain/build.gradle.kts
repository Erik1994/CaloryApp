plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

apply {
    from("$rootDir/base-module.gradle")
}

android {
    namespace = ProjectConfig.onBoardingDomainNameSpace
}

dependencies {
    "implementation"(project(Modules.core))
}