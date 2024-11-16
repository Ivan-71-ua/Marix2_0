package com.aplication.maxtrix2_0.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String login;

    @Column(nullable = false)
    private String password;

    private String fullName;

    @Column(nullable = false)
    private Boolean isAdmin = false;
}
