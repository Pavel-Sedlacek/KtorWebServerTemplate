import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val hibernate_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"
    kotlin("kapt") version "1.5.31"
}

group = "org.knism"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-sessions:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // https://mvnrepository.com/artifact/org.springframework.security/spring-security-core
    implementation("org.springframework.security:spring-security-core:5.5.2")

    // https://mvnrepository.com/artifact/org.hibernate/hibernate-core
    implementation("org.hibernate:hibernate-core:$hibernate_version")
    implementation("org.hibernate:hibernate-jpamodelgen:$hibernate_version")

    // https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager
    implementation("org.hibernate:hibernate-entitymanager:$hibernate_version")

    implementation("org.mariadb.jdbc:mariadb-java-client:2.7.3")

//    kapt("org.hibernate:hibernate-jpamodelgen:$hibernate_version")
}