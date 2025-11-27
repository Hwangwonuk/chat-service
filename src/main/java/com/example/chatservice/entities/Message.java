package com.example.chatservice.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Message {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String text;

    LocalDateTime createdAt;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @JoinColumn(name = "chatroom_id")
    @ManyToOne
    private Chatroom chatroom;

    @Builder
    public Message(String text, Member member, Chatroom chatroom, LocalDateTime createdAt) {
        this.text = text;
        this.member = member;
        this.chatroom = chatroom;
        this.createdAt = createdAt;
    }
}
