package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servise.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.servise.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showAllUsers(Principal principal, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("admin", userService.findUserByUsername(principal.getName()));
        model.addAttribute("newUser", new User());
        model.addAttribute("rolesAdd", roleService.getUniqAllRoles());
        return "admin";
    }

//    @GetMapping("/{id}")
//    public String showUser(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.getUser(id));
//        model.addAttribute("titleTable", "Страница пользователя:");
//        return "user";
//    }
//
//    @GetMapping("/addUser")
//    public String addNewUser(Model model, @ModelAttribute("user") User user) {
//        List<Role> roles = roleService.getUniqAllRoles();
//        model.addAttribute("rolesAdd", roles);
//        return "newUser";
//    }

    @PostMapping
    public String addCreateNewUser(@ModelAttribute("user") User user) {
        userService.createNewUser(user);
        return "redirect:/admin";
    }

//    @GetMapping("/{id}/editUser")
//    public String edit(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.getUser(id));
//        List<Role> roles = roleService.getUniqAllRoles();
//        model.addAttribute("rolesAdd", roles);
//        return "edit";
//    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
