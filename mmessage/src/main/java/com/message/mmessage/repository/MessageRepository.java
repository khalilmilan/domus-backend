package com.message.mmessage.repository;

import com.message.mmessage.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findMessageByIdDiscussionOrderByDate(Long idDiscussion);
}
