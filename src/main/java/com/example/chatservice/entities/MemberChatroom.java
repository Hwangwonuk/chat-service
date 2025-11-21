package com.example.chatservice.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class MemberChatroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @JoinColumn(name = "chatroom_id")
    @ManyToOne
    private Chatroom chatroom;

    @Builder
    public MemberChatroom(Member member, Chatroom chatroom) {
        this.member = member;
        this.chatroom = chatroom;
    }
}
