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
    namespace = ProjectConfig.trackerDataNameSpace
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.trackerDomain))
}