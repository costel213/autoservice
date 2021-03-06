package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.User;
import com.costelmitrea.autoservice.services.SimpleGrantedAuthorityService;
import com.costelmitrea.autoservice.services.UserService;
import com.costelmitrea.autoservice.util.AttributesName;
import com.costelmitrea.autoservice.util.ViewsName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private SimpleGrantedAuthorityService simpleGrantedAuthorityService;

    @GetMapping("/register")
    public String initRegistrationForm(Map<String, Object> model){
        User user = new User();
        model.put(AttributesName.USER, user);
        return ViewsName.SIGNUP_FORM;
    }

    @PostMapping("/register")
    public String processRegistrationForm(@Validated User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ViewsName.SIGNUP_FORM;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            this.userService.save(user);
            return "redirect:/registeredSuccessfully";
        }
    }

    @GetMapping("/registeredSuccessfully")
    public String homeRegistration() {
        return ViewsName.INDEX_AFTER_REGISTRATION;
    }
}
