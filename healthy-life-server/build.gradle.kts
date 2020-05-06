plugins  {
    id("org.springframework.boot")

    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    // Kotlin
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
	useJUnitPlatform()
}