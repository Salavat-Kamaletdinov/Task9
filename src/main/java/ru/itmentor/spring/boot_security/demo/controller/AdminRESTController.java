package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")

public class AdminRESTController {
    private final UserService userService;

    public AdminRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        return allUsers;
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return user;
    }
    @PostMapping("/new")
    public User create(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.saveUser(user);
        return user;
    }

    @PatchMapping("/update/{id}")
    public User update(@RequestBody User user, @PathVariable("id") Long id) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setId(id);
        user.setPassword(encodedPassword);
        userService.updateUser(user);
        return user;
    }



    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "User" + id + " deleted";
    }

}
