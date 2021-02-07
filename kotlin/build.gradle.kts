import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
  id("org.springframework.boot") version "2.4.2"
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
  kotlin("jvm") version "1.4.21"
  kotlin("plugin.spring") version "1.4.21"
}

java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
  implementation("org.springframework.boot:spring-boot-starter-amqp")

  implementation("org.springframework.experimental:spring-graalvm-native:0.8.5")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.getByName<BootBuildImage>("bootBuildImage") {
  imageName = "websummit.com/webhooks/${project.name}"
  builder = "paketobuildpacks/builder:tiny"
  environment = mapOf(
    "BP_BOOT_NATIVE_IMAGE" to "1",
    "BP_BOOT_NATIVE_IMAGE_BUILD_ARGUMENTS" to listOf(
      "-Dspring.spel.ignore=false",
      "-Dspring.native.remove-yaml-support=true"
    ).joinToString(" ")
  )
}
