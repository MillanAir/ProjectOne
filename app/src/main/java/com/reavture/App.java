/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.reavture;

import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;

public class App {

    public static void main(String[] args) {

        Javalin server = Javalin.create(JavalinConfig::enableCorsForAllOrigins);
        server.start(8080);
    }
}