import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Compose {
    const val composeVersion = "1.5.0"
    const val composeCompilerVersion = "1.4.8"
    private const val composeBomVersion = "2023.03.00"
    private const val composeMaterialVersion = "1.1.1"
    const val material = "androidx.compose.material3:material3:$composeMaterialVersion"
    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val graphics = "androidx.compose.ui:ui-graphics:$composeVersion"
    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
    const val compiler = "androidx.compose.compiler:compiler:$composeCompilerVersion"
    const val composeBom = "androidx.compose:compose-bom:$composeBomVersion"

    private const val navigationVersion = "2.6.0"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"

    private const val activityComposeVersion = "1.7.2"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"

    private const val lifecycleVersion = "2.6.1"
    const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"
}

fun DependencyHandler.compose() {
    implementation(Compose.activityCompose)
    platformImpl(platformDependecy = Compose.composeBom)
    implementation(Compose.ui)
    implementation(Compose.graphics)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.material)
    implementation(Compose.navigation)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.viewModelCompose)
}