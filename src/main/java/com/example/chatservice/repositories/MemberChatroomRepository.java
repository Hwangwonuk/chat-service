package com.example.chatservice.repositories;

import com.example.chatservice.entities.MemberChatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberChatroomRepository extends JpaRepository<MemberChatroom, Long> {

}
