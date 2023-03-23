plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testImplementation("com.codeborne:selenide-proxy:6.12.4")
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.7")
}

tasks.test {
    useJUnitPlatform()
    maxParallelForks = 2
}
