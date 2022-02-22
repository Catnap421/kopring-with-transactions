dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:4.4.0")
    testImplementation("io.kotest:kotest-assertions-core:4.4.0")
    testImplementation("io.kotest:kotest-property:4.4.0")
    testImplementation("io.mockk:mockk:1.12.2")
}
