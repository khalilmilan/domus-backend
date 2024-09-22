package com.mticket.mticket.service;

import com.mticket.mticket.dto.TicketDTO;
import com.mticket.mticket.exception.TicketException;
import com.mticket.mticket.model.Ticket;

import java.util.List;

public interface TicketService {
    TicketDTO saveTicket(TicketDTO ticketDto);

    List<TicketDTO> getALLTicket();

    void deleteTicket(Long idTicket) throws TicketException;

    TicketDTO getTicket(Long idTicket) throws TicketException;

    void updateTicket(Long id, Ticket ticket) throws TicketException;

    List<TicketDTO> getTicketByUser(Long idUser);
    List<TicketDTO> getTicketByAffectedTo(Long affectedTO);
    List<TicketDTO> getTicketByProject(Long idProject);
}
