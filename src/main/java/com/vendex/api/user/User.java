package com.vendex.api.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@EqualsAndHashCode
@Table(name = "users")
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String email;

    private String password;

    public static User of(User input) {
        return new User(null, input.getName(), input.getEmail(), input.getPassword());
    }

}