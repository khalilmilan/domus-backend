package com.mevent.mevent.repository;

import com.mevent.mevent.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findEventsByIdUser(Long idUser);
}
