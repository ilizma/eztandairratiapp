object Project {
    val buildGradle by lazy { "com.android.tools.build:gradle:${Versions.buildGradleVersion}" }
    val kotlinGradle by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}" }
    val navigation by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}" }
    val googleServices by lazy { "com.google.gms:google-services:${Versions.gooleServicesVersion}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}" }
}

object Rx {
    val kotlin by lazy { "io.reactivex.rxjava3:rxkotlin:${Versions.rxKotlinVersion}" }
    val java by lazy { "io.reactivex.rxjava3:rxjava:${Versions.rxJavaVersion}" }
    val binding by lazy { "com.jakewharton.rxbinding4:rxbinding:${Versions.rxBindingVersion}" }
}

object Android {
    val appcompat by lazy { "androidx.appcompat:appcompat:${Versions.supportVersion}" }
    val legacy by lazy { "androidx.legacy:legacy-support-v4:${Versions.legacyVersion}" }
}

object AndroidKtx {
    val core by lazy { "androidx.core:core-ktx:${Versions.coreKtxVersion}" }
}

object AndroidUI {
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}" }
    val material by lazy { "com.google.android.material:material:${Versions.materialVersion}" }
    val shimmer by lazy { "com.facebook.shimmer:shimmer:${Versions.shimmerVersion}" }
    val lottie by lazy { "com.airbnb.android:lottie:${Versions.lottieVersion}" }
}

object Architecture {
    val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}" }
    val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}" }
    val lifecycleCommon by lazy { "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}" }
    val lifecycleReactivestreams by lazy { "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.lifecycleVersion}" }
}

object CustomActivityOnCrash {
    val customactivityoncrash by lazy { "cat.ereza:customactivityoncrash:${Versions.customactivityoncrashVersion}" }
}

object Network {
    val moshi by lazy { "com.squareup.moshi:moshi:${Versions.moshiVersion}" }
    val moshiKotlin by lazy { "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}" }
    val moshiAdapters by lazy { "com.squareup.moshi:moshi-adapters:${Versions.moshiVersion}" }
    val moshiConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverterVersion}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}" }
    val retrofitAdapter by lazy { "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofitVersion}" }
    val okhttpLogging by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingVersion}" }
    val chucker by lazy { "com.github.chuckerteam.chucker:library:${Versions.chuckerVersion}" }
    val chuckerNoOp by lazy { "com.github.chuckerteam.chucker:library-no-op:${Versions.chuckerVersion}" }
}

object Dagger {
    val dagger by lazy { "com.google.dagger:dagger:${Versions.daggerVersion}" }
    val android by lazy { "com.google.dagger:dagger-android:${Versions.daggerVersion}" }
    val processor by lazy { "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}" }
    val compiler by lazy { "com.google.dagger:dagger-compiler:${Versions.daggerVersion}" }
    val support by lazy { "com.google.dagger:dagger-android-support:${Versions.daggerVersion}" }
}

object Annotation {
    val annotation by lazy { "androidx.annotation:annotation:${Versions.annotationVersion}" }
}

object Timber {
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timberVersion}" }
}

object Firebase {
    val analytics by lazy { "com.google.firebase:firebase-analytics:${Versions.firebaseAnalyticsVersion}" }
    val messaging by lazy { "com.google.firebase:firebase-messaging:${Versions.firebaseMessagingVersion}" }
}

object Test {
    val mockitoKotlin by lazy { "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinVersion}" }
    val mockitoCore by lazy { "org.mockito:mockito-core:${Versions.mockitoCoreVersion}" }
    val mockitoInline by lazy { "org.mockito:mockito-inline:${Versions.mockitoInlineVersion}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoVersion}" }
    val junit by lazy { "junit:junit:${Versions.junitVersion}" }
    val runner by lazy { "androidx.test:runner:${Versions.supportTestVersion}" }
    val rules by lazy { "androidx.test:rules:${Versions.supportTestVersion}" }
    val core by lazy { "androidx.test:core:${Versions.testCoreVersion}" }
    val extJunit by lazy { "androidx.test.ext:junit:${Versions.testExtJunitVersion}" }
}
