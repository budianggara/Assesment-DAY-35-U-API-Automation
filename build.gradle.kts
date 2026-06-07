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
        implementation("org.apache.logging.log4j:log4j-api") { version { strictly("2.20.0") } }
        implementation("org.apache.logging.log4j:log4j-core") { version { strictly("2.20.0") } }
    }

    // Framework Inti pengujian REST API
    testImplementation("io.rest-assured:rest-assured:5.4.0") {
        exclude(group = "org.apache.groovy", module = "groovy")
        exclude(group = "org.apache.groovy", module = "groovy-xml")
        exclude(group = "org.codehaus.groovy", module = "groovy")
        exclude(group = "org.codehaus.groovy", module = "groovy-xml")
    }

    // Runner Pengujian
    testImplementation("org.testng:testng:7.9.0")
}

tasks.test {
    useTestNG()
}
