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

package dependencies

/**
 * Project dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${BuildDependenciesVersions.KOTLIN}"
    const val KOTLIN_REFLECT =
        "org.jetbrains.kotlin:kotlin-reflect:${BuildDependenciesVersions.KOTLIN}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildDependenciesVersions.APPCOMPAT}"
    const val MATERIAL =
        "com.google.android.material:material:${BuildDependenciesVersions.MATERIAL}"
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.COROUTINES}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.COROUTINES}"
    const val ROOM = "androidx.room:room-runtime:${BuildDependenciesVersions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${BuildDependenciesVersions.ROOM}"
    const val NAVIGATION_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val NAVIGATION_UI =
        "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val NAVIGATION_DYNAMIC_FEATURES_FRAGMENT =
        "androidx.navigation:navigation-dynamic-features-fragment:${BuildDependenciesVersions.NAVIGATION}"
    const val LIFECYCLE_EXTENSIONS =
        "androidx.lifecycle:lifecycle-extensions:${BuildDependenciesVersions.LIFECYCLE}"
    const val LIFECYCLE_LIVEDATA =
        "androidx.lifecycle:lifecycle-livedata-ktx:${BuildDependenciesVersions.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildDependenciesVersions.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime-ktx:${BuildDependenciesVersions.LIFECYCLE}"
    const val CORE_KTX = "androidx.core:core-ktx:${BuildDependenciesVersions.CORE_KTX}"
    const val FRAGMENT_KTX =
        "androidx.fragment:fragment-ktx:${BuildDependenciesVersions.FRAGMENT_KTX}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.CONSTRAINT_LAYOUT}"
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${BuildDependenciesVersions.SWIPE_REFRESH_LAYOUT}"
    const val PAGING = "androidx.paging:paging-runtime:${BuildDependenciesVersions.PAGING}"
    const val DAGGER = "com.google.dagger:dagger:${BuildDependenciesVersions.DAGGER}"
    const val DAGGER_HILT =
        "com.google.dagger:hilt-android:${BuildDependenciesVersions.DAGGER}"
    const val ANDROIDX_HILT_LIFECYCLE =
        "androidx.hilt:hilt-lifecycle-viewmodel:${BuildDependenciesVersions.ANDROIDX_HILT}"
    const val TIMBER = "com.jakewharton.timber:timber:${BuildDependenciesVersions.TIMBER}"
    const val GSON =
        "com.google.code.gson:gson:${BuildDependenciesVersions.GSON}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_CONVERTER =
        "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.RETROFIT}"
    const val OKHTTP_LOGGER =
        "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.OKHTTP_LOGGER}"
    const val LOGGER = "com.github.ihsanbal:LoggingInterceptor:${BuildDependenciesVersions.LOGGER}"
    const val COIL = "io.coil-kt:coil:${BuildDependenciesVersions.COIL}"
    const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"
    const val FIREBASE_MESSAGING = "com.google.firebase:firebase-messaging-ktx"
    const val PLAY_CORE = "com.google.android.play:core:${BuildDependenciesVersions.PLAY_CORE}"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    const val LOGGING_INTERCEPTOR =
        "com.github.ihsanbal:LoggingInterceptor:${BuildDependenciesVersions.LOGGING_INTERCEPTOR}"
    const val SNACKY = "com.github.matecode:Snacky:${BuildDependenciesVersions.SNACKY}"
    const val PERSIAN_DATE =
        "com.github.samanzamani.persiandate:PersianDate:${BuildDependenciesVersions.PERSIAN_DATE}"
    const val GOOGLE_MAPS =
        "com.google.maps.android:maps-v3-ktx:${BuildDependenciesVersions.GOOGLE_MAPS_V3}"
    const val GOOGLE_MAPS_UTILS =
        "com.google.maps.android:maps-utils-v3-ktx:${BuildDependenciesVersions.GOOGLE_MAPS_V3}"
    const val GOOGLE_MAPS_LIBS =
        "com.google.android.libraries.maps:maps:${BuildDependenciesVersions.GOOGLE_MAPS}"
    const val GOOGLE_MAPS_UTILS_LIBS =
        "com.google.maps.android:android-maps-utils-v3:${BuildDependenciesVersions.GOOGLE_MAPS_UTILS}"
    const val GOOGLE_BASE =
        "com.google.android.gms:play-services-base:${BuildDependenciesVersions.GOOGLE_BASE}"
    const val GOOGLE_AUTH =
        "com.google.android.gms:play-services-auth:${BuildDependenciesVersions.GOOGLE_AUTH}"
    const val GOOGLE_BASEMENT =
        "com.google.android.gms:play-services-basement:${BuildDependenciesVersions.GOOGLE_BASEMENT}"
    const val GOOGLE_GCM =
        "com.google.android.gms:play-services-gcm:${BuildDependenciesVersions.GOOGLE_GCM}"
    const val GOOGLE_LOCATION =
        "com.google.android.gms:play-services-location:${BuildDependenciesVersions.GOOGLE_LOCATION}"
    const val GOOGLE_TASKS =
        "com.google.android.gms:play-services-tasks:${BuildDependenciesVersions.GOOGLE_TASKS}"
    const val GOOGLE_AUTO =
        "com.google.auto.value:auto-value-annotations:${BuildDependenciesVersions.GOOGLE_AUTO}"
    const val PERMISSION_DISPATCHER =
        "org.permissionsdispatcher:permissionsdispatcher:${BuildDependenciesVersions.PERMISSION_DISPATCHER}"
    const val ARROW_CORE = "io.arrow-kt:arrow-core:${BuildDependenciesVersions.ARROW}"
    const val ARROW_SYNTAX = "io.arrow-kt:arrow-syntax:${BuildDependenciesVersions.ARROW}"
    const val MATERIAL_DIALOG_CORE =
        "com.afollestad.material-dialogs:core:${BuildDependenciesVersions.MATERIAL_DIALOG}"
    const val MATERIAL_DIALOG_INPUT =
        "com.afollestad.material-dialogs:input:${BuildDependenciesVersions.MATERIAL_DIALOG}"
    const val MATERIAL_DIALOG_DATETIME =
        "com.afollestad.material-dialogs:datetime:${BuildDependenciesVersions.MATERIAL_DIALOG}"
    const val MATERIAL_DIALOG_LIFECYCLE =
        "com.afollestad.material-dialogs:lifecycle:${BuildDependenciesVersions.MATERIAL_DIALOG}"
    const val MATERIAL_DIALOG_BOTTOMSHEETS =
        "com.afollestad.material-dialogs:bottomsheets:${BuildDependenciesVersions.MATERIAL_DIALOG}"
    const val VVALIDATOR =
        "com.afollestad:vvalidator:${BuildDependenciesVersions.VVALIDATOR}"
    const val VVALIDATOR_HOTFIX =
        "com.github.mohammadestk:vvalidator:${BuildDependenciesVersions.VVALIDATOR_HOTFIX}"
    const val SUN_DATE_PICKER =
        "com.alirezaafkar:sundatepicker:${BuildDependenciesVersions.SUN_DATE_PICKER}"
    const val IMAGE_PICKER =
        "com.github.dhaval2404:imagepicker:${BuildDependenciesVersions.IMAGE_PICKER}"
    const val INLINE_ACTIVITY =
        "com.github.florent37:inline-activity-result-kotlin:${BuildDependenciesVersions.INLINE_ACTIVITY}"
    const val MATERIAL_SPINNER =
        "com.github.tiper:MaterialSpinner:${BuildDependenciesVersions.MATERIAL_SPINNER}"
    const val CHOCOBAR =
        "com.github.Pradyuman7:ChocoBar:${BuildDependenciesVersions.CHOCOBAR}"
    const val EVENT_BUS = "org.greenrobot:eventbus:${BuildDependenciesVersions.EVENT_BUS}"
    const val MULTIDEX = "androidx.multidex:multidex:${BuildDependenciesVersions.MULTIDEX}"
    const val EXO_PLAYER =
        "com.google.android.exoplayer:exoplayer:${BuildDependenciesVersions.EXO_PLAYER}"
    const val ANDROIDX_MEDIA = "androidx.media:media:${BuildDependenciesVersions.ANDROIDX_MEDIA}"
    const val VIEW_BINDING_DELEGATE =
        "com.kirich1409.viewbindingpropertydelegate:viewbindingpropertydelegate:${BuildDependenciesVersions.VIEW_BINDING_DELEGATE}"
    const val
        RETROFIT_GRAPHQL =
        "com.ramkishorevs.graphqlretrofitconverter:graphqlconverter:${BuildDependenciesVersions.RETROFIT_GRAPHQL}"
    const val
        VIEWPAGER = "androidx.viewpager2:viewpager2:${BuildDependenciesVersions.VIEWPAGER}"
    const val GRAVITY_SNAP_HELPER =
        "com.github.rubensousa:gravitysnaphelper:${BuildDependenciesVersions.GRAVITY_SNAP_HELPER}"
    const val SWITCHER_VIEW =
        "jp.pocket7878.switcherview:switcherview:${BuildDependenciesVersions.SWITCHER_VIEW}"
    const val WILLIAM_CHART =
        "com.diogobernardino:williamchart:${BuildDependenciesVersions.WILLIAM_CHART}"
    const val WILLIAM_CHART_TOOLTIP_SLIDER =
        "com.diogobernardino.williamchart:tooltip-slider:${BuildDependenciesVersions.WILLIAM_CHART}"
    const val WILLIAM_CHART_TOOLTIP_POINTS =
        "com.diogobernardino.williamchart:tooltip-points:${BuildDependenciesVersions.WILLIAM_CHART}"
    const val MPCHART = "com.github.PhilJay:MPAndroidChart:${BuildDependenciesVersions.MPCHART}"
    const val POWER_SPINNER =
        "com.github.skydoves:powerspinner:${BuildDependenciesVersions.POWER_SPINNER}"
    const val NICE_SPINNER =
        "com.github.arcadefire:nice-spinner:${BuildDependenciesVersions.NICE_SPINNER}"
    const val CIRCULAR_PROGRESS =
        "com.mikhaellopez:circularprogressbar:${BuildDependenciesVersions.CIRCULAR_PROGRESS}"
    const val EMULATOR_DETECTOR =
        "com.github.framgia:android-emulator-detector:${BuildDependenciesVersions.EMULATOR_DETECTOR}"
    const val OTP_VIEW =
        "com.github.mukeshsolanki:android-otpview-pinview:${BuildDependenciesVersions.OTP_VIEW}"
    const val IMAGE_VIEWER =
        "com.github.stfalcon:stfalcon-imageviewer:${BuildDependenciesVersions.IMAGE_VIEWER}"
    const val IMAGE_SLIDE_SHOW =
        "com.github.denzcoskun:ImageSlideshow:${BuildDependenciesVersions.IMAGE_SLIDE_SHOW}"
    const val SPAN = "me.gujun.android:span:${BuildDependenciesVersions.SPAN}"
}
