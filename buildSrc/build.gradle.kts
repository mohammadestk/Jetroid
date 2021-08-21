/*
 * Copyright 2021 MohammadEsteki.ir
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
    mavenCentral()
    gradlePluginPortal()
    maven("https://jitpack.io")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://plugins.gradle.org/m2/")
    maven("https://ci.android.com/builds/submitted/5837096/androidx_snapshot/latest/repository")
}

object PluginsVersions {
    const val GRADLE_ANDROID = "7.0.1"
    const val GOOGLE_SERVICE = "4.3.10"
    const val GRADLE_VERSIONS = "0.39.0"
    const val KOTLIN = "1.5.21"
    const val NAVIGATION = "2.3.5"
    const val FIREBASE_CRASHLYTICS = "2.7.1"
    const val DAGGER_HILT = "2.38.1"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("com.google.gms:google-services:${PluginsVersions.GOOGLE_SERVICE}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginsVersions.GRADLE_VERSIONS}")
    implementation("com.google.firebase:firebase-crashlytics-gradle:${PluginsVersions.FIREBASE_CRASHLYTICS}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginsVersions.DAGGER_HILT}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("org.jetbrains.kotlin:kotlin-allopen:${PluginsVersions.KOTLIN}")
}

gradlePlugin {
    plugins.register("jetroid-kotlin-library") {
        id = "jetroid-kotlin-library"
        implementationClass = "commons.JetroidKotlinLibraryPlugin"
    }
    plugins.register("jetroid-android-library") {
        id = "jetroid-android-library"
        implementationClass = "commons.JetroidAndroidLibraryPlugin"
    }
}