/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.spike.eventstreams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest {
    public static final String BASE_DIR = "src/test/resources";
    public static final String OUTPUT_BASE_DIR = "src/test/java";

    @Test
    void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }

}
