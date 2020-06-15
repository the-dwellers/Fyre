import org.apache.tools.ant.filters.ReplaceTokens
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    kotlin("kapt") version "1.3.72"
}

group = "io.github.the-dwellers"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven {
        name = "PaperMC"
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }
    maven {
        name = "Sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    maven {
        name = "JitPack"
        url = uri("https://jitpack.io")
    }
    maven {
        name = "dmulloy2"
        url = uri("https://repo.dmulloy2.net/nexus/repository/public/")
    }
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.destroystokyo.paper", "paper-api", "1.15.1-R0.1-SNAPSHOT")

    compileOnly("org.reflections", "reflections", "0.9.10")

    compileOnly("com.google.code.gson", "gson", "2.8.6")
    compileOnly("org.projectlombok", "lombok", "1.18.10")

    annotationProcessor("org.projectlombok", "lombok", "1.18.10")

    testImplementation("org.junit.jupiter", "junit-jupiter", "5.5.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_14
    targetCompatibility = JavaVersion.VERSION_14
}

kapt {
}

tasks {
    jar {
        manifest {
            val attributes = mapOf("Main-Class" to "io.github.the-dwellers.fyre.FyrePlugin")
            attributes(attributes)
        }
    }

    processResources {
        val path = sourceSets.main.get().resources.srcDirs
        from(path) {
            val tokens = mapOf("version" to version)
            filter<ReplaceTokens>(tokens)
        }
    }

    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}
