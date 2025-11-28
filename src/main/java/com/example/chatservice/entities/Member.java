package com.example.chatservice.entities;

import com.example.chatservice.enums.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String email;

    String nickName;

    String name;

    String password;

    String phoneNumber;

    LocalDate birthDay;

    @Enumerated(EnumType.STRING)
    Gender gender;

    String role;

    @Builder
    public Member(String email, String nickName, String name, String password, String phoneNumber, LocalDate birthDay, Gender gender, String role) {
        this.email = email;
        this.nickName = nickName;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.gender = gender;
        this.role = role;
    }

    public void updatePassword(String password, String confirmPassword, PasswordEncoder passwordEncoder) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalStateException("패스워드가 일치하지 않습니다.");
        }

        this.password = passwordEncoder.encode(password);
    }
}
