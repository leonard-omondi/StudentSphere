package com.leonard;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Javalin app = Javalin.create().start(9000);

    }
}
