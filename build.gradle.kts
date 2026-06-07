plugins {
    id("java")
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Membaca file data JSON dan Excel
    implementation("org.json:json:20240303")
    implementation("org.apache.poi:poi-ooxml:5.2.5")

    // Sinkronisasi paksa versi Log4j agar tidak crash / bertabrakan versi
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")

    constraints {
        implementation("org.apache.logging.log4j:log4j-api") {
            version { strictly("2.20.0") }
        }
        implementation("org.apache.logging.log4j:log4j-core") {
            version { strictly("2.20.0") }
        }
    }

    // Framework Inti pengujian REST API (Sudah dibersihkan dari blok exclude)
    testImplementation("io.rest-assured:rest-assured:5.4.0")

    // Runner Pengujian
    testImplementation("org.testng:testng:7.9.0")
}

// Menjamin kompiler menggunakan UTF-8 agar aman dari error 'unmappable character'
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    useTestNG()
}
