plugins {
    application
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

dependencies {
    api(project(":scanner_core"))

    // should get the code data from language analyser
    implementation("com.phodal.chapi:chapi-ast-kotlin:2.0.0-beta.4") {
        exclude(group = "com.ibm.icu", module = "icu4j")
    }
    implementation("com.phodal.chapi:chapi-ast-java:2.0.0-beta.4") {
        exclude(group = "com.ibm.icu", module = "icu4j")
    }

    implementation("org.eclipse.jgit:org.eclipse.jgit:6.0.0.202111291000-r")

    testImplementation("io.mockk:mockk:1.12.3")
    testImplementation("org.assertj:assertj-core:3.22.0")
}

application {
    mainClass.set("org.archguard.scanner.core.AnalyserKt")
}

tasks {
    shadowJar {
        dependencies {
            exclude(dependency("org.jetbrains.kotlin:.*:.*"))
            exclude(dependency("org.jetbrains.kotlinx:.*:.*"))
        }
        minimize()
    }
}
