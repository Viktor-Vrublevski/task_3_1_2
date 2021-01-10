package org.mentor.project.controller;

import org.mentor.project.model.Role;
import org.mentor.project.model.User;
import org.mentor.project.service.RoleService;
import org.mentor.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users")
    public String tableWithUser(ModelMap model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("email", username);
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        List<User> list = userService.getAll();
        model.addAttribute("users", list);
        return "table";
    }

    @GetMapping(value = "/create")
    public String createUser(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("email", user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("createUser", new User());
        return "create";
    }

    @PostMapping(value = "/create")
    public String createUser(@ModelAttribute("createUser") User user) {

        user.setPassword(encoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        if (user.getRole().equals("user")) {
            roles.add(roleService.getRole(2));
            user.setRoles(roles);
        }
        if (user.getRole().equals("admin")) {
            roles.add(roleService.getRole(1));
            roles.add(roleService.getRole(2));
            user.setRoles(roles);
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(User user) {
        userService.delete(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/getUser")
    @ResponseBody
    public Optional<User> getUser(Long id) {
        return userService.findById(id);
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute("user") User user) {
        List<Role> roles = new ArrayList<>();
        if(user.getRole().equals("user")) {
            roles.add(roleService.getRole(2));
            user.setRoles(roles);
        }
        if(user.getRole().equals("admin")) {
            roles.add(roleService.getRole(1));
            roles.add(roleService.getRole(2));
            user.setRoles(roles);
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/user-admin")
    public String pageUserAdmin(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("email", user.getUsername());
        model.addAttribute("user", user);
        return "user-admin";
    }

}

