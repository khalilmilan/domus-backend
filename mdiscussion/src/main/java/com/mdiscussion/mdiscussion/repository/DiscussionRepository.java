package com.mdiscussion.mdiscussion.repository;

import com.mdiscussion.mdiscussion.model.Discussion;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion,Long> {

    List<Discussion> findDiscussionByIdUser1OrIdUser2(Long idUser1,Long idUser2);

    @Query("SELECT CASE " +
            "WHEN d.idUser1 = :currentUserId THEN d.idUser2 " +
            "WHEN d.idUser2 = :currentUserId THEN d.idUser1 " +
            "END " +
            "FROM Discussion d " +
            "WHERE :currentUserId IN (d.idUser1, d.idUser2)")
    List<Long> findUsersInDiscussionWith(@Param("currentUserId") Long currentUserId);
}
