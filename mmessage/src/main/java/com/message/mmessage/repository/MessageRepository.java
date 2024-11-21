package com.message.mmessage.repository;

import com.message.mmessage.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findMessageByIdDiscussionOrderByDateDesc(Long idDiscussion);
    Message findTopByIdDiscussionOrderByDateDesc(Long idDiscussion);
    @Query("SELECT COUNT(m) FROM Message m WHERE m.status = 0 AND m.idDiscussion = :idDiscussion AND m.idSender <> :idUser")
    Long countByIdDiscussionAndStatus(Long idDiscussion,Long idUser);

    List<Message> findMessageByIdDiscussionAndIdSenderAndStatus(Long idDiscussion,Long idSender, int status);
}
