package org.mentor.project.controller;

import org.mentor.project.model.User;
import org.mentor.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/user")
    public String pageUser(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("email",user.getUsername());
        model.addAttribute("user", user);
        return "user";
    }
}
