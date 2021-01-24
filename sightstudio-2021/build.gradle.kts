plugins {
    java
    kotlin("jvm") version "1.4.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junit = "5.3.1"
val mockk = "1.10.4"
val mockito = "3.7"
dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit")

    testImplementation("io.mockk:mockk:$mockk")

//    testImplementation("org.mockito:mockito-core:$mockito")
//    testImplementation("org.mockito:mockito-inline:$mockito")
}

tasks {
    test {
        useJUnitPlatform()
    }
}