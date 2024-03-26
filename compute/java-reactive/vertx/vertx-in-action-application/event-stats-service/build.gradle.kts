dependencies {
    implementation(project(":commons"))

    implementation("io.vertx:vertx-rx-java3")
    implementation("io.vertx:vertx-web-client")
    implementation("io.vertx:vertx-kafka-client") {
        exclude("org.slf4j")
        exclude("log4j")
    }

    testImplementation("io.vertx:vertx-junit5-rx-java3")
    testImplementation("io.vertx:vertx-web")
}
