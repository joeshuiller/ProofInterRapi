plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.dokka)
}
// Configuración opcional para personalizar la documentación
tasks.dokkaHtml {
    outputDirectory.set(layout.buildDirectory.dir("documentation/html"))

    dokkaSourceSets.named("main") {
        moduleName.set("Proof Inter Rapi App")

        // Incluye una descripción de la arquitectura en la página de inicio
        includes.from("README.md", "module.md")

        // No documentar archivos generados (como BuildConfig)
        suppressGeneratedFiles.set(true)
    }
}
android {
    namespace = "com.softsaenz.proofinterrapi"
    compileSdk = 36

    buildTypes {
        debug {
            isMinifyEnabled = false // No ofuscar en debug para poder debugear
            applicationIdSuffix = ".debug"
            defaultConfig {
                applicationId = "com.softsaenz.proofinterrapi"
                minSdk = 24
                targetSdk = 36
                versionCode = 1
                versionName = "1.0"

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                val enchiladaKey = project.findProperty("API_KEY_HINCHADA") ?: ""
                val users = project.findProperty("USERS") ?: ""
                val id = project.findProperty("ID") ?: ""
                val idUsers = project.findProperty("ID_USERS") ?: ""
                val idCenterServices = project.findProperty("ID_CENTER_SERVICES") ?: ""
                val nameCenterService = project.findProperty("NAME_CENTER_SERVICES") ?: ""
                val idApplication = project.findProperty("ID_APPLICATION") ?: ""
                val urlApi = project.findProperty("URL_API") ?: ""


                // La inyectamos como un String de Java (por eso las comillas escapadas)
                buildConfigField("String", "API_KEY_HINCHADA", "\"$enchiladaKey\"")
                buildConfigField("String", "USERS", "\"$users\"")
                buildConfigField("String", "ID", "\"$id\"")
                buildConfigField("String", "ID_USERS", "\"$idUsers\"")
                buildConfigField("String", "ID_CENTER_SERVICES", "\"$idCenterServices\"")
                buildConfigField("String", "NAME_CENTER_SERVICES", "\"$nameCenterService\"")
                buildConfigField("String", "ID_APPLICATION", "\"$idApplication\"")
                buildConfigField("String", "URL_API", "\"$urlApi\"")


            }
        }
        release {
            isMinifyEnabled = false // No ofuscar en debug para poder debugear
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            defaultConfig {
                applicationId = "com.softsaenz.proofinterrapi"
                minSdk = 24
                targetSdk = 36
                versionCode = 1
                versionName = "1.0"

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                val enchiladaKey = project.findProperty("API_KEY_HINCHADA") ?: ""
                val users = project.findProperty("USERS") ?: ""
                val id = project.findProperty("ID") ?: ""
                val idUsers = project.findProperty("ID_USERS") ?: ""
                val idCenterServices = project.findProperty("ID_CENTER_SERVICES") ?: ""
                val nameCenterService = project.findProperty("NAME_CENTER_SERVICES") ?: ""
                val idApplication = project.findProperty("ID_APPLICATION") ?: ""
                val urlApi = project.findProperty("URL_API") ?: ""


                // La inyectamos como un String de Java (por eso las comillas escapadas)
                buildConfigField("String", "API_KEY_HINCHADA", "\"$enchiladaKey\"")
                buildConfigField("String", "USERS", "\"$users\"")
                buildConfigField("String", "ID", "\"$id\"")
                buildConfigField("String", "ID_USERS", "\"$idUsers\"")
                buildConfigField("String", "ID_CENTER_SERVICES", "\"$idCenterServices\"")
                buildConfigField("String", "NAME_CENTER_SERVICES", "\"$nameCenterService\"")
                buildConfigField("String", "ID_APPLICATION", "\"$idApplication\"")
                buildConfigField("String", "URL_API", "\"$urlApi\"")


            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    // Hilt Core
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.navigation.compose)

    // Integración específica con Compose Navigation
    implementation(libs.hilt.navigation.compose)
    // Usando el bundle definido en el TOML
    implementation(libs.bundles.retrofit)


    //coroutines
    implementation(libs.kotlinx.coroutines)
    implementation(libs.coil.compose)

    // Implementación base
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    // Procesador de anotaciones (KSP es mucho más rápido que Kapt)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.jwt)

    // CameraX
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)

    // ML Kit
    implementation(libs.play.services.mlkit.face.detection)

    // tensorflow
    implementation(libs.tensorflow.lite)
    implementation(libs.tensorflow.lite.support)
    // Logging
    implementation(libs.timber)
}