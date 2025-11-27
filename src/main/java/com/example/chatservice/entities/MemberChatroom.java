package com.example.chatservice.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class MemberChatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    LocalDateTime lastCheckedAt;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @JoinColumn(name = "chatroom_id")
    @ManyToOne
    private Chatroom chatroom;

    @Builder
    public MemberChatroom(LocalDateTime lastCheckedAt, Member member, Chatroom chatroom) {
        this.lastCheckedAt = lastCheckedAt;
        this.member = member;
        this.chatroom = chatroom;
    }

    public void updateLastCheckedAt() {
        this.lastCheckedAt = LocalDateTime.now();
    }
}
