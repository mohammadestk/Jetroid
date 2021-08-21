dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        jcenter() // Warning: this repository is going to shut down soon
        maven("https://jitpack.io")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://plugins.gradle.org/m2/")
        maven("https://ci.android.com/builds/submitted/5837096/androidx_snapshot/latest/repository")
    }
}

rootProject.name = "Jetroid"
include(
    ":app",
    ":commons:ui",
    ":libraries:utils",
    ":libraries:test_utils"
)
