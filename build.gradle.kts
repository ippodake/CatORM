plugins {
    id("java")
    `maven-publish`
}

group = ""
version = "1.0.0"




repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.guava:guava:33.4.8-jre")
    implementation("org.projectlombok:lombok:1.18.38")
    annotationProcessor ("org.projectlombok:lombok:1.18.38")
}

tasks.test {
    useJUnitPlatform()
}