package com.TPOBackend.TPOBackend.Controllers.TestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    @GetMapping("Test")
    public String test() {
        return "Hello World";
    }
}
