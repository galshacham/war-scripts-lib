import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.7.10"
  application
  kotlin("plugin.serialization") version "1.7.10"
}

group = "wsl.game"
version = "1.0.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation("wsl.game:engine:1.0.0")
  implementation("wsl.game:runner:1.0.0")
  testImplementation(kotlin("test"))
  testImplementation("io.mockk:mockk:1.12.4")
  testImplementation("org.assertj:assertj-core:3.22.0")
  testImplementation("com.github.stefanbirkner:system-lambda:1.2.0")
}


tasks.test {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

application {
  mainClass.set("main.kt")
}