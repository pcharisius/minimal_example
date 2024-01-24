plugins {
    kotlin("jvm") version "1.9.21"
    id("com.github.imflog.kafka-schema-registry-gradle-plugin") version "1.12.0"
    id("org.openapi.generator") version "7.1.0"

}

// enable buildscript for com.github.imflog.kafka-schema-registry-gradle-plugin:1.11.1
//buildscript {
//    repositories {
//        gradlePluginPortal()
//        maven {
//            url = uri("https://packages.confluent.io/maven/")
//        }
//        maven {
//            url = uri("https://jitpack.io")
//        }
//    }
//}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}



tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/specs/test-api-v1.0.yaml")
    outputDir.set(layout.buildDirectory.dir("generated").get().asFile.absolutePath)
    configFile.set("$rootDir/src/main/resources/api-config.yaml")
    templateDir.set("$rootDir/src/main/resources/openapigen-templates")
    configOptions.set(mapOf("useSpringBoot3" to "true"))
}

