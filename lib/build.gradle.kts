/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.8.3/userguide/building_java_projects.html
 */

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.1")
    testImplementation("com.codeborne:selenide:5.20.1")
    implementation("com.browserup:browserup-proxy:2.1.2")
    testImplementation("com.browserup:browserup-proxy-core:2.1.2")
    testImplementation("io.netty:netty-all:4.1.60.Final")
    testRuntimeOnly("org.slf4j:slf4j-simple:1.7.30")
}

tasks.test {
    useJUnitPlatform()
    maxParallelForks = 2
}
