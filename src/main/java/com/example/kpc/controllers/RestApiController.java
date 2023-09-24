package com.example.kpc.controllers;

import com.example.kpc.entity.Animal;
import com.example.kpc.entity.Book;
import com.example.kpc.entity.Sick;
import com.example.kpc.entity.User;
import com.example.kpc.repositories.AnimalRepository;
import com.example.kpc.repositories.BookRepository;
import com.example.kpc.repositories.SickRepository;
import com.example.kpc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected AnimalRepository animalRepository;
    @Autowired
    protected BookRepository bookRepository;
    @Autowired
    protected SickRepository sickRepository;

    /* ------------------------------------------- */

    @GetMapping("/sick")
    public Sick getSick(@RequestParam UUID sickId) {
        return sickRepository.findById(sickId).get();
    }

    @DeleteMapping("/sick")
    public void deleteSick(@RequestParam UUID sickId) {
        sickRepository.deleteById(sickId);
    }

    @GetMapping("/sicks")
    public List<Sick> getSicks() {
        return sickRepository.findAll();
    }

    @PostMapping("/sick")
    public void saveSick(@RequestBody Sick sick) {
        sickRepository.save(sick);
    }

    /* ------------------------------------------- */

    @GetMapping("/book")
    public Book getBook(@RequestParam UUID bookId) {
        return bookRepository.findById(bookId).get();
    }

    @DeleteMapping("/book")
    public void deleteBook(@RequestParam UUID bookId) {
        bookRepository.deleteById(bookId);
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/book")
    public void saveBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    /* ------------------------------------------- */

    @GetMapping("/animal")
    public Animal getAnimal(@RequestParam UUID animalId) {
        return animalRepository.findById(animalId).get();
    }

    @DeleteMapping("/animal")
    public void deleteAnimal(@RequestParam UUID animalId) {
        animalRepository.deleteById(animalId);
    }

    @GetMapping("/animals")
    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    @PostMapping("/animal")
    public void saveAnimal(@RequestBody Animal animal) {
        animalRepository.save(animal);
    }

    /* ------------------------------------------- */

    @GetMapping("/user")
    public User getUser(@RequestParam UUID userId) {
        return userRepository.findById(userId).get();
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam UUID userId) {
        userRepository.deleteById(userId);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public void saveSick(@RequestBody User user) {
        userRepository.save(user);
    }

    /* ------------------------------------------- */
}
