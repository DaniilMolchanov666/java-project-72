plugins {
    application
    id("checkstyle")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
    mainClass.set("hexlet.code.App")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("io.javalin:javalin:5.6.3")
    implementation("io.javalin:javalin-rendering:5.6.2")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation("gg.jte:jte:3.1.4")
    compileOnly("org.projectlombok:lombok:1.18.30")
    implementation("com.konghq:unirest-java:4.0.0-RC2")
    implementation ("org.jsoup:jsoup:1.16.2")


    implementation("org.eclipse.dirigible:dirigible-database-h2:8.17.1")

    implementation("com.h2database:h2:2.2.224")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}