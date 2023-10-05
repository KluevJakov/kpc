package com.example.kpc.entity.DTO;

import com.example.kpc.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AuthResponse {
    private int status;
    private User user;
}
