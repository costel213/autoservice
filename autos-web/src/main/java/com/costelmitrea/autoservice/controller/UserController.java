package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.SimpleGrantedAuthority;
import com.costelmitrea.autoservice.model.User;
import com.costelmitrea.autoservice.services.SimpleGrantedAuthorityService;
import com.costelmitrea.autoservice.services.UserService;
import com.costelmitrea.autoservice.util.AttributesName;
import com.costelmitrea.autoservice.util.ViewsName;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class UserController {

    private final UserService userService;
    private final SimpleGrantedAuthorityService simpleGrantedAuthorityService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, SimpleGrantedAuthorityService simpleGrantedAuthorityService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.simpleGrantedAuthorityService = simpleGrantedAuthorityService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute(AttributesName.ROLES)
    public Collection<SimpleGrantedAuthority> populateRoles() {
        return this.simpleGrantedAuthorityService.findAll();
    }

    @GetMapping("/users/{userId}/edit")
    public String initUpdateForm(@PathVariable("userId") Long userId, Model model) {
        User user = this.userService.findById(userId);
        model.addAttribute(user);
        return ViewsName.CREATE_OR_UPDATE_USER_FORM;
    }

    @PostMapping("/users/{userId}/edit")
    public String processUpdateForm(@Validated User user, BindingResult bindingResult,
                                    @PathVariable("userId") Long userId) {
        if(bindingResult.hasErrors()) {
            return ViewsName.CREATE_OR_UPDATE_USER_FORM;
        } else {
            User userDetails = this.userService.findById(userId);
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setPassword(userDetails.getPassword());
            user.setId(userId);
            this.userService.save(user);
            return "redirect:/usersList";
        }
    }

    @GetMapping("/users/{userId}/deleted")
    public String deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteById(userId);
        return "redirect:/usersList";
    }

    @GetMapping("/usersList")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return ViewsName.USERS_LIST;
    }
}
