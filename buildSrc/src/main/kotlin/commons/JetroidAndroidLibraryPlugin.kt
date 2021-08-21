package commons

import BuildAndroidConfig
import BuildProductDimensions
import ProductFlavorDevelop
import ProductFlavorProduction
import ProductFlavorQA
import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.*
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

@Suppress("UnstableApiUsage")
class JetroidAndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        applyPlugins(target)
        configureAndroid(target)
        applyDependencies(target)
    }

    private fun applyDependencies(target: Project) {
        target.dependencies {
            implementation(Dependencies.KOTLIN)
            implementation(Dependencies.COROUTINES)
            implementation(Dependencies.COROUTINES_ANDROID)
            implementation(Dependencies.DAGGER)
            implementation(Dependencies.TIMBER)

            kapt(AnnotationProcessorsDependencies.DAGGER)

//                testImplementation(project(BuildModules.Libraries.TEST_UTILS))
            addTestsDependencies()
        }
    }

    private fun configureAndroid(target: Project) {
        target.android().apply {
            compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION
            defaultConfig {
                minSdk = BuildAndroidConfig.MIN_SDK_VERSION
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            kotlinOptions().apply {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }

            flavorDimensions.add(BuildProductDimensions.ENVIRONMENT)

            productFlavors {
                ProductFlavorDevelop.libraryCreate(this)
                ProductFlavorQA.libraryCreate(this)
                ProductFlavorProduction.libraryCreate(this)
            }

            sourceSets {
                getByName("main") {
                    java.srcDir("src/main/kotlin")
                }
                getByName("test") {
                    java.srcDir("src/test/kotlin")
                }
            }

            lint {
                //                lintConfig = rootProject.file(".lint/config.xml")
                isCheckAllWarnings = true
                isWarningsAsErrors = true
            }

            testOptions {
                unitTests.isIncludeAndroidResources = true
                unitTests.isReturnDefaultValues = true
            }

        }
    }

    private fun applyPlugins(target: Project) {
        target.apply(plugin = "com.android.library")
        target.apply(plugin = "kotlin-android")
        target.apply(plugin = "kotlin-kapt")
        target.apply(plugin = "kotlin-allopen")
    }

}