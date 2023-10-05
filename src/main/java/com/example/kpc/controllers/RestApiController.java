package com.example.kpc.controllers;

import com.example.kpc.configurations.Constants;
import com.example.kpc.entity.Animal;
import com.example.kpc.entity.Book;
import com.example.kpc.entity.DTO.AuthResponse;
import com.example.kpc.entity.DTO.AuthStatus;
import com.example.kpc.entity.Sick;
import com.example.kpc.entity.User;
import com.example.kpc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    @Autowired
    private RoleRepository roleRepository;

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

    @PostMapping("/reg")
    public Boolean reg(@RequestBody User user) {
        Optional<User> userCheck = userRepository.findByEmail(user.getEmail());

        if (userCheck.isPresent()) {
            return false;
        }
        user.setRole(roleRepository.getReferenceById(Constants.USER_ROLE_ID));
        userRepository.save(user);
        return true;
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password) {
        Optional<User> user = userRepository.findByEmail(login);

        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                if (user.get().isActivated()) {
                    return new AuthResponse(AuthStatus.SUCCESS.ordinal(), user.get());
                }
                return new AuthResponse(AuthStatus.DEACTIVATED_USER.ordinal(), null);
            }
            return new AuthResponse(AuthStatus.INCORRECT_PASSWORD.ordinal(), null);
        }
        return new AuthResponse(AuthStatus.NO_SUCH_USER.ordinal(), null);
    }
}
