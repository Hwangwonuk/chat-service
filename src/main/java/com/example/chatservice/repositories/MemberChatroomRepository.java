package com.example.chatservice.repositories;

import com.example.chatservice.entities.MemberChatroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberChatroomRepository extends JpaRepository<MemberChatroom, Long> {

    Boolean existsByMemberIdAndChatroomId(Long memberId, Long chatroomId);

    void deleteByMemberIdAndChatroomId(Long memberId, Long chatroomId);

    List<MemberChatroom> findAllByMemberId(Long memberId);

    Optional<MemberChatroom> findByMemberIdAndChatroomId(Long memberId, Long currentChatroomId);
}
