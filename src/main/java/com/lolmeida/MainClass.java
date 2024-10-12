package com.lolmeida;

import io.quarkus.runtime.Quarkus;


public class MainClass {
    public static void main(String... args) {
        System.out.println("Starting Quarkus application...");
        Quarkus.run(args);
    }

}
