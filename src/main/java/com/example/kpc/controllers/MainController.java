package com.example.kpc.controllers;

import com.example.kpc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected RoleRepository roleRepository;
    @Autowired
    protected AnimalRepository animalRepository;
    @Autowired
    protected BookRepository bookRepository;
    @Autowired
    protected SickRepository sickRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin";
    }

    @GetMapping("/book")
    public String book(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book";
    }

    @GetMapping("/sick")
    public String sick(Model model) {
        model.addAttribute("sicks", sickRepository.findAll());
        return "sick";
    }

    @GetMapping("/animal")
    public String animal(Model model) {
        model.addAttribute("animals", animalRepository.findAll());
        return "animal";
    }

    @GetMapping("/successLogin")
    public String successLogin() {
        return "redirect:/admin";
    }
}
