package com.costelmitrea.autoservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/oups")
    public String ExceptionHandler() {
        throw new RuntimeException("Expected: controller used to showcase what " +
                "happens when an exception is thrown.");
    }
}
