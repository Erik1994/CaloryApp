plugins {
    id("com.android.library")
    kotlin("android")
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = ProjectConfig.trackerDataNameSpace
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.trackerDomain))

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.gsonConverter)

    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
}