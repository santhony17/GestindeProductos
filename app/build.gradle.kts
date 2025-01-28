plugins {
    alias(libs.plugins.android.application) // Plugin para aplicaciones Android
    alias(libs.plugins.google.gms.google.services) // Plugin para servicios de Google (Firebase)
}

android {
    namespace = "com.example.gestiondeproductos" // Define el namespace para el proyecto
    compileSdk = 34 // Nivel de API de compilación

    defaultConfig {
        applicationId = "com.example.gestindeproductos" // ID del paquete de la aplicación
        minSdk = 26 // Nivel mínimo de API compatible
        targetSdk = 34 // Nivel de API objetivo
        versionCode = 1 // Código de versión
        versionName = "1.0" // Nombre de versión

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Runner de pruebas
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Desactiva la ofuscación para builds de release
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // Compatibilidad con Java 11
        targetCompatibility = JavaVersion.VERSION_11 // Compatibilidad con Java 11
    }

    buildFeatures {
        viewBinding = true // Activa ViewBinding (opcional, pero útil)
    }
}

dependencies {
    // Dependencias de AndroidX
    implementation(libs.appcompat) // Compatibilidad con actividades
    implementation(libs.material) // Componentes de Material Design
    implementation(libs.activity) // Compatibilidad con actividades modernas
    implementation(libs.constraintlayout) // Layouts flexibles

    // Firebase dependencies
    implementation(platform("com.google.firebase:firebase-bom:32.2.3")) // BOM de Firebase
    implementation("com.google.firebase:firebase-firestore") // Firestore
    implementation("com.google.firebase:firebase-analytics") // Analytics

    // Dependencias para pruebas
    testImplementation(libs.junit) // Pruebas unitarias
    androidTestImplementation(libs.ext.junit) // Pruebas Android con JUnit
    androidTestImplementation(libs.espresso.core) // Framework de pruebas Espresso
}
