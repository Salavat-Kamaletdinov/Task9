package ru.itmentor.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.Set;


@Controller
@RequestMapping("/admin")

public class AdminController {
    private UserService userService;



    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @Secured("ROLE_ADMIN")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("people", userService.getAllUsers());
        return "show_all";
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public String getUserById(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    @Secured("ROLE_ADMIN")
    public String createNewPerson(@ModelAttribute("user") User user, ModelMap model) {
        model.addAttribute("roles", userService.findAllRoles());
        return "new";
    }

    @PostMapping()
    @Secured("ROLE_ADMIN")
    public String create(@ModelAttribute("user") User user, @RequestParam Set<Long> roleIds) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.saveUser(user, roleIds);

        return "redirect:/admin";
    }
    @GetMapping("/{id}/edit")
    @Secured("ROLE_ADMIN")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", userService.findAllRoles());
        return "edit";
    }

    @PatchMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }


}

