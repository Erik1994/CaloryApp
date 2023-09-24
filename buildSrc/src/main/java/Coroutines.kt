import org.gradle.api.artifacts.dsl.DependencyHandler

object Coroutines {
    const val version = "1.6.0"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
}

fun DependencyHandler.coroutine() {
    implementation(Coroutines.coroutines)
}