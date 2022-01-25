package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.util.ViewsName;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", ""})
    public String index() {
        return ViewsName.INDEX;
    }

    @GetMapping("/home")
    public String home() {
        return ViewsName.INDEX_AFTER_LOGIN;
    }
}
