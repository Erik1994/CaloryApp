plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}
apply {
    from("$rootDir/compose-module.gradle")
}

android {
    namespace = ProjectConfig.trackerPresentationNameSpace
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.trackerDomain))
    "implementation"(Coil.coilCompose)
}