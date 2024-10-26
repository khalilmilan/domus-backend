package com.mevent_user.mevent_user.repository;

import com.mevent_user.mevent_user.model.EventUser;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventUserRepository extends JpaRepository<EventUser,Long> {

    List<EventUser> findEventUserByIdEvent(Long idEvent);
    EventUser findEventUserByIdEventAndIdUser(Long idEvent,Long idUser);
    @Query("SELECT u.idUser FROM EventUser u WHERE u.idEvent = :eventId")
    List<Long> findUsersInEvent(@Param("eventId") Long eventId);

    @Query("SELECT u.idEvent FROM EventUser u WHERE u.idUser = :idUser")
    List<Long> findEventByParticipant(@Param("idUSer") Long idUser);
}
