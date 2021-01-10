package org.mentor.project.controller;

import org.mentor.project.model.Role;
import org.mentor.project.model.User;
import org.mentor.project.service.RoleService;
import org.mentor.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder encoder;


    @GetMapping(value = "/login")
    public String login() {
        if (roleService.getAllRoles().size() == 0) {
            roleService.create();
        }
        if (userService.getAll().size() == 0) {
            User user = new User();
            List<Role> roles = new ArrayList<>();
            roles.add(roleService.getRole(1));
            roles.add(roleService.getRole(2));
            user.setRoles(roles);
            user.setUsername("admin@mail.ru");
            user.setPassword(encoder.encode("ADMIN"));
            user.setName("ADMIN");
            user.setSurname("ADMIN");
            user.setAge((byte)100);
            userService.save(user);
        }
        return "login";
    }

}

