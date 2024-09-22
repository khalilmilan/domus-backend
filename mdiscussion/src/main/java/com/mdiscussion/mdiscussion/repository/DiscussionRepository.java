package com.mdiscussion.mdiscussion.repository;

import com.mdiscussion.mdiscussion.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion,Long> {

    List<Discussion> findDiscussionByIdUser1OrIdUser2(Long idUser1,Long idUser2);
}
