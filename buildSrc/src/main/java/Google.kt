import org.gradle.api.artifacts.dsl.DependencyHandler

object Google {
    private const val materialVersion = "1.5.0-alpha04"
    const val material = "com.google.android.material:material:$materialVersion"

    private const val gsonVersion = "2.10.1"
    const val gson = "com.google.code.gson:gson:$gsonVersion"
}

fun DependencyHandler.google() {
    implementation(Google.material)
    implementation(Google.gson)
}