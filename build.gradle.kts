plugins {
    java
    id("io.freefair.lombok") version "6.5.0.3"
}

group = "com.deltalang"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.9.0")
}
