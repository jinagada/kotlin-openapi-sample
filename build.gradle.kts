import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.41"
    id("org.springframework.boot") version "2.1.7.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.noarg") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

buildscript {
    dependencies {
        classpath("org.openapitools:openapi-generator-gradle-plugin:4.1.1")
    }
}

apply(plugin = "org.openapi.generator")
apply(from="gradle/swagger.gradle")

group = "com.example.openapi"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testCompile("com.nhaarman:mockito-kotlin:1.5.0")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    // springfox
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-bean-validators:2.9.2")
    // openapi를 쓰는 다른 예제에서는 아래의 의존성이 필요가 없는데 컴파일이 안되서 어쩔수 없이 넣음.
    implementation("org.openapitools:jackson-databind-nullable:0.2.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.test {
    useJUnitPlatform()
}
