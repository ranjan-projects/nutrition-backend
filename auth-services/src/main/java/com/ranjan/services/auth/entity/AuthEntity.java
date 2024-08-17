package com.ranjan.services.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "user_entity")
@Data
public class AuthEntity {

    @Id
    private Long userId;

    private String email;

    private String password;


}
