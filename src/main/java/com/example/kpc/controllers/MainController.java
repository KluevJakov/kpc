package com.example.kpc.controllers;

import com.example.kpc.repositories.AnimalRepository;
import com.example.kpc.repositories.BookRepository;
import com.example.kpc.repositories.SickRepository;
import com.example.kpc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    protected UserRepository userRepository;
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
}
