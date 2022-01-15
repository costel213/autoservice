package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.User;
import com.costelmitrea.autoservice.repositories.SimpleGrantedAuthorityRepository;
import com.costelmitrea.autoservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SimpleGrantedAuthorityRepository simpleGrantedAuthorityRepository;

    @ModelAttribute("grantedAuthority")
    public String getDefaultAuthority() {
        return "ROLE_USER";
    }

    @GetMapping("/register")
    public String initRegistrationForm(Map<String, Object> model){
        User user = new User();
        model.put("user", user);
        return "signupForm";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@Validated User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signupForm";
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return "redirect:/registeredSuccessfully";
        }
    }

    @GetMapping("/registeredSuccessfully")
    public String homeRegistration() {
        return "indexAfterRegistration";
    }
}
