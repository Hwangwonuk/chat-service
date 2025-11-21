package com.example.chatservice.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Chatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "chatroom")
    private Set<MemberChatroom> memberSet;

    @Builder
    public Chatroom(String title, LocalDateTime createdAt, Set<MemberChatroom> memberSet) {
        this.title = title;
        this.createdAt = createdAt;
        this.memberSet = memberSet;
    }
}
