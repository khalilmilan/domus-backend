package com.forum.mforum.repository;

import com.forum.mforum.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum, Long> {

    List<Forum> findForymByIdEvent(Long idEvent);
}
