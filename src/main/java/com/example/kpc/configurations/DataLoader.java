package com.example.kpc.configurations;

import com.example.kpc.repositories.RoleRepository;
import com.example.kpc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleRepository.insertRole(Constants.USER_ROLE_ID,
                "USER",
                "Пользователь");

        roleRepository.insertRole(Constants.ADMIN_ROLE_ID,
                "ADMIN",
                "Администратор");

        userRepository.insertUser(Constants.ADMIN_USER_ID,
                true,
                "",
                new Date(),
                "admin@admin.com",
                "Админов Админ Админович",
                "password",
                "+79991323432",
                Constants.ADMIN_ROLE_ID);
    }
}
