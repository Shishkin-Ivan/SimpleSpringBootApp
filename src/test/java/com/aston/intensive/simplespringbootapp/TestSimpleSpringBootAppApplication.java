package com.aston.intensive.simplespringbootapp;

import org.springframework.boot.SpringApplication;

public class TestSimpleSpringBootAppApplication {

    public static void main(String[] args) {
        SpringApplication.from(SimpleSpringBootApp::main).with(TestcontainersConfiguration.class).run(args);
    }

}
