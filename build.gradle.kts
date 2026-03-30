plugins {
	kotlin("jvm") version "2.3.20"
	id("com.github.johnrengelman.shadow") version "8.1.1"
}

val targetJavaVersion = 21
val group = "com.battletrident"
val version = "1.0.0"

repositories {
	mavenCentral()
	maven("https://repo.papermc.io/repository/maven-public/") {
		name = "papermc-repo"
	}
	maven("https://oss.sonatype.org/content/groups/public/") {
		name = "sonatype"
	}
}
dependencies {
	compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}
kotlin {
	jvmToolchain(targetJavaVersion)
}

tasks {
	processResources {
		val props = mapOf("version" to version)
		inputs.properties(props)
		filteringCharset = "UTF-8"
		filesMatching("plugin.yml") {
			expand(props)
		}
	}
	
	build {
		dependsOn("shadowJar")
	}
	
	shadowJar {
		destinationDirectory = file("./run/plugins/")
		archiveFileName = "plugin.jar"
	}
}