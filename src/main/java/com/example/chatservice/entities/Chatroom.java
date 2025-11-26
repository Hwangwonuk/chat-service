package com.example.chatservice.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
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
    private Set<MemberChatroom> memberSet = new HashSet<>();

    @Builder
    public Chatroom(String title, LocalDateTime createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }

    public MemberChatroom addMember(Member member) {
        MemberChatroom memberChatroom = MemberChatroom.builder()
                .member(member)
                .chatroom(this)
                .build();

        this.memberSet.add(memberChatroom);

        return memberChatroom;
    }
}
