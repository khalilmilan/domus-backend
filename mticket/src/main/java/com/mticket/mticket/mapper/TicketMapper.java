package com.mticket.mticket.mapper;

import com.mticket.mticket.dto.ProjectDTO;
import com.mticket.mticket.dto.TicketDTO;
import com.mticket.mticket.dto.UserDTO;
import com.mticket.mticket.model.Ticket;

import java.util.ArrayList;

public class TicketMapper {

    public static TicketDTO mapToTicketDto(Ticket ticket){
        TicketDTO ticketDto = new TicketDTO(
                ticket.getIdTicket(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getAffectedTo(),
                ticket.getStatus(),
                ticket.getIdUser(),
                ticket.getIdProject(),
                new UserDTO(),
                new UserDTO()
              //  new ProjectDTO()
        );
        return ticketDto;
    }


    public static Ticket mapToTicket(TicketDTO ticketDto){
        Ticket ticket = new Ticket(
                ticketDto.getIdTicket(),
                ticketDto.getTitle(),
                ticketDto.getDescription(),
                ticketDto.getAffectedTo(),
                ticketDto.getStatus(),
                ticketDto.getIdUser(),
                ticketDto.getIdProject()
        );
        return ticket;
    }
}
