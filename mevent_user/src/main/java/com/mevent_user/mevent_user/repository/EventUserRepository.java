package com.mevent_user.mevent_user.repository;

import com.mevent_user.mevent_user.model.EventUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventUserRepository extends JpaRepository<EventUser,Long> {

    List<EventUser> findEventUserByIdEvent(Long idEvent);
    EventUser findEventUserByIdEventAndIdUser(Long idEvent,Long idUser);
}
