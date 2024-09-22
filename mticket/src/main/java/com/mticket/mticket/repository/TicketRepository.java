package com.mticket.mticket.repository;

import com.mticket.mticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findTicketByIdProject(Long idProject);
    List<Ticket> findTicketByIdUser(Long idUser);
    List<Ticket> findTicketByAffectedTo(Long affectedTo);

}
