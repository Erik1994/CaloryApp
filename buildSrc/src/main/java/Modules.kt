import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Modules {
    const val app = ":app"

    const val core = ":core"
    const val coreUi = ":core-ui"

    const val onboardingDomain = ":onboarding:onboarding_domain"
    const val onboardingPresentation = ":onboarding:onboarding_presentation"

    const val trackerData = ":tracker:tracker_data"
    const val trackerDomain = ":tracker:tracker_domain"
    const val trackerPresentation = ":tracker:tracker_presentation"
}

fun DependencyHandler.modules() {
    projectImpl(Modules.core)
    projectImpl(Modules.coreUi)
    projectImpl(Modules.onboardingPresentation)
    projectImpl(Modules.onboardingDomain)
    projectImpl(Modules.trackerPresentation)
    projectImpl(Modules.trackerDomain)
    projectImpl(Modules.trackerData)
}

fun DependencyHandler.singleModule(path: String) {
    projectImpl(path)
}