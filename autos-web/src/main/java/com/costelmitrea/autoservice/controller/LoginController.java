package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.util.ViewsName;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    private String login() {
        return ViewsName.LOGIN_FORM;
    }
}
