const val kotlinVersion = "1.5.0-M1"
const val hiltVersion = "2.33-beta"

object BuildPlugins {
    object Version {
        const val androidBuildToolsVersion = "7.0.0-alpha08"
        const val versionsVersion = "0.33.0"
        const val detektVersion = "1.14.1"
        const val ktlintVersion = "9.4.0"
        const val canidropjetifierVersion = "0.5"
    }

    const val versions = "com.github.ben-manes.versions"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
    const val canidropjetifier = "com.github.plnice.canidropjetifier"

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Version.androidBuildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val hiltAndroidPlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"

    const val versionsPlugin = "com.github.ben-manes:gradle-versions-plugin:${Version.versionsVersion}"
    const val detektPlugin = "$detekt:detekt-gradle-plugin:${Version.detektVersion}"
    const val ktlintPlugin = "org.jlleitschuh.gradle:ktlint-gradle:${Version.ktlintVersion}"
    const val canidropjetifierPlugin = "com.github.plnice:canidropjetifier:${Version.canidropjetifierVersion}"
}

object AndroidSdk {
    const val min = 31
    const val compile = "android-S"
    const val target = 31
}


object Libraries {
    private object Versions {
        const val appCompat = "1.3.0-beta01"
        const val ktxActivity = "1.3.0-alpha03"
        const val ktxFragment = "1.3.0"
        const val material = "1.4.0-alpha01"
        const val constraintLayout = "2.1.0-alpha1"
        const val lifecycle = "2.3.0"
        const val hilt = "1.0.0-alpha03"

        const val junit = "4.13.1"
        const val androidXJunit = "1.1.3-alpha04"
        const val espressoCore = "3.4.0-alpha04"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val ktxActivity = "androidx.activity:activity-ktx:${Versions.ktxActivity}"
    const val ktxFragment = "androidx.fragment:fragment-ktx:${Versions.ktxFragment}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltLifecycleViewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"

    const val junit = "junit:junit:${Versions.junit}"
    const val androidXJunit = "androidx.test.ext:junit:${Versions.androidXJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}
