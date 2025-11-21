package com.example.chatservice.entities;

import com.example.chatservice.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String email;

    String nickName;

    String name;

    String phoneNumber;

    LocalDate birthDay;

    @Enumerated(EnumType.STRING)
    Gender gender;

    String role;

}
