package ru.itmentor.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;



@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(ModelMap model) {
        model.addAttribute("people", userService.getAllUsers());
        return "show_all";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("person", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String createNewPerson(@ModelAttribute("person") User user) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("person", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}




//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//    private final UserService userService;
//
//    public AdminController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/users")
//    public String findAll(Model model) {
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("users", users);
//        return "admin/index";
//    }
//
//    @GetMapping("/user-create")
//    public String createUserForm(User user) {
//        return "admin/user-create";
//    }
//
//    @PostMapping("/user-create")
//    public String createUser(User user) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("user-delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.getUserById(id);
//        return "redirect:/admin";
//    }
//    @GetMapping("/user-update/{id}")
//    public String updateUserForm(@PathVariable("id") Long id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "admin/user-update";
//    }
//    @PostMapping("/user-update")
//    public String updateUser(User user) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }
//
//
//}
