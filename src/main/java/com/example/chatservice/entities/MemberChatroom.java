package com.example.chatservice.entities;

import jakarta.persistence.*;

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
    
}
