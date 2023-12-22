package com.scrap.demo.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;

    @Column(name = "username", unique = true, nullable = false, length = 64)
    private String username;

    @Column(name = "password_hashed", nullable = false, length = 64)
    private String passwordHashed;

    @Column(name = "password_salt", nullable = false, length = 32)
    private String passwordSalt;

    @Column(name = "email", unique = true, nullable = false, length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "signup_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date signupDate;

    // toString
    @Override
    public String toString() {
        return "User - {" +
                " userID='" + getUserID() + "'" +
                ", username='" + getUsername() + "'" +
                ", passwordHashed='" + getPasswordHashed() + "'" +
                ", passwordSalt='" + getPasswordSalt() + "'" +
                ", email='" + getEmail() + "'" +
                ", role='" + getRole() + "'" +
                ", signupDate='" + getSignupDate() + "'" +
                "}";
    }
}
