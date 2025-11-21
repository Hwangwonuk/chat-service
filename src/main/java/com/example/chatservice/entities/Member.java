package com.example.chatservice.entities;

import com.example.chatservice.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
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

    @Builder
    public Member(String email, String nickName, String name, String phoneNumber, LocalDate birthDay, Gender gender, String role) {
        this.email = email;
        this.nickName = nickName;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.gender = gender;
        this.role = role;
    }

}
