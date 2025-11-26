package com.example.chatservice.services;

import com.example.chatservice.entities.Chatroom;
import com.example.chatservice.entities.Member;
import com.example.chatservice.entities.MemberChatroom;
import com.example.chatservice.entities.Message;
import com.example.chatservice.repositories.ChatroomRepository;
import com.example.chatservice.repositories.MemberChatroomRepository;
import com.example.chatservice.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatroomRepository chatroomRepository;
    private final MemberChatroomRepository memberChatroomRepository;
    private final MessageRepository messageRepository;

    public Chatroom createChatroom(Member member, String title) {
        Chatroom chatroom = Chatroom.builder()
                .title(title)
                .createdAt(LocalDateTime.now())
                .build();

        chatroom = chatroomRepository.save(chatroom);

        // 채팅방을 만든 사용자가 기본적으로 참여되도록 설정
        MemberChatroom memberChatroom = chatroom.addMember(member);

        memberChatroomRepository.save(memberChatroom);

        return chatroom;
    }

    public Boolean joinChatroom(Member member, Long chatroomId) {
        if (memberChatroomRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)) {
            log.info("이미 참여한 채팅방입니다.");
            return false;
        }

        Chatroom chatroom = chatroomRepository.findById(chatroomId)
                .orElseThrow(() -> new RuntimeException("채팅방이 존재하지 않습니다."));

        MemberChatroom memberChatroom = MemberChatroom.builder()
                .member(member)
                .chatroom(chatroom)
                .build();

        memberChatroomRepository.save(memberChatroom);

        return true;
    }

    @Transactional
    public Boolean leaveChatroom(Member member, Long chatroomId) {
        if (!memberChatroomRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)) {
            log.info("참여하지 않은 방입니다.");
            return false;
        }

        memberChatroomRepository.deleteByMemberIdAndChatroomId(member.getId(), chatroomId);

        return true;
    }

    public List<Chatroom> getChatroomList(Member member) {
        List<MemberChatroom> memberChatroomList = memberChatroomRepository.findAllByMemberId(member.getId());

        return memberChatroomList.stream()
                .map(MemberChatroom::getChatroom)
                .toList();
    }

    public Message saveMessage(Member member, Long chatroomId, String text) {

        Chatroom chatroom = chatroomRepository.findById(chatroomId).get();

        Message message = Message.builder()
                .member(member)
                .chatroom(chatroom)
                .text(text)
                .build();

        return messageRepository.save(message);
    }

    public List<Message> getMessageList(Long chatroomId) {
        return messageRepository.findAllByChatroomId(chatroomId);
    }

}
