package com.develop.api.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/example")
public class ExampleController {
    @GetMapping(path="/docker")
    public String testDocker() {
        return "Hola live reloading";
    }
}
