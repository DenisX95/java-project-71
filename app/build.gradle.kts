plugins {
    application
    java
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.6")

}

tasks.test {
    useJUnitPlatform()
}

application {
    // Входная точка
    mainClass.set("hexlet.code.App")
}

