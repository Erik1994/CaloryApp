plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}
apply {
    from("$rootDir/compose-module.gradle")
}

android {
    namespace = ProjectConfig.trackerPresentationNameSpace
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.trackerDomain))
    "implementation"(Coil.coilCompose)
}