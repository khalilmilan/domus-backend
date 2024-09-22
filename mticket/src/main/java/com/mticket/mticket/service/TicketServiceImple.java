package com.mticket.mticket.service;

import com.mticket.mticket.dto.ProjectDTO;
import com.mticket.mticket.dto.TicketDTO;
import com.mticket.mticket.dto.UserDTO;
import com.mticket.mticket.exception.TicketException;
import com.mticket.mticket.mapper.TicketMapper;
import com.mticket.mticket.model.Ticket;
import com.mticket.mticket.repository.TicketRepository;
import com.mticket.mticket.service.client.ProjectFeignClient;
import com.mticket.mticket.service.client.UserFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceImple implements TicketService{
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImple.class);
    private UserFeignClient userFeignClient;
    private TicketRepository ticketRepository;
    private ProjectFeignClient projectFeignClient;
    @Override
    public TicketDTO saveTicket(TicketDTO ticketDto) {
        Ticket ticket = TicketMapper.mapToTicket(ticketDto);
        ticket.setCreatedAt(LocalDateTime.now());
        Ticket savedTicket = ticketRepository.save(ticket);
        TicketDTO savedTicketDto = TicketMapper.mapToTicketDto(savedTicket);
        return savedTicketDto;
    }

    @Override
    public List<TicketDTO> getALLTicket() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDTO> ticketsDto= new ArrayList<>();
        if (tickets.size() > 0) {
            for (Ticket ticket : tickets) {
                TicketDTO dto = TicketMapper.mapToTicketDto(ticket);
                UserDTO userDto = userFeignClient.getUser(dto.getIdUser()).getBody();
                dto.setUser(userDto);
                UserDTO affecteToDto = userFeignClient.getUser(dto.getAffectedTo()).getBody();
                dto.setUserAffectedTo(affecteToDto);
               // ProjectDTO project = projectFeignClient.getProject(dto.getIdProject()).getBody();
              //  dto.setProject(project);
                ticketsDto.add(dto);
            }
            return ticketsDto;
        }else {
            return new ArrayList<TicketDTO>();
        }
    }

    @Override
    public void deleteTicket(Long idTicket) throws TicketException {
        Optional<Ticket> ticketOptional = ticketRepository.findById(idTicket);
        if (!ticketOptional.isPresent()) {
            throw new TicketException(TicketException.NotFoundException(idTicket));
        } else {
            ticketRepository.deleteById(idTicket);
        }
    }

    @Override
    public TicketDTO getTicket(Long idTicket) throws TicketException {
        Optional<Ticket> ticketOptional = ticketRepository.findById(idTicket);
        if (!ticketOptional.isPresent()) {
            throw new TicketException(TicketException.NotFoundException(idTicket));
        }else {
            TicketDTO dto = TicketMapper.mapToTicketDto(ticketOptional.get());
            UserDTO userDto = userFeignClient.getUser(dto.getIdUser()).getBody();
            dto.setUser(userDto);
            UserDTO affecteToDto = userFeignClient.getUser(dto.getAffectedTo()).getBody();
            dto.setUserAffectedTo(affecteToDto);
           // ProjectDTO project = projectFeignClient.getProject(dto.getIdProject()).getBody();
           // dto.setProject(project);
            return dto;
        }
    }

    @Override
    public void updateTicket(Long id, Ticket ticket) throws TicketException {
        Optional<Ticket> ticketWithId = ticketRepository.findById(id);
        if(ticketWithId.isPresent())
        {
            Ticket ticketToUpdate=ticketWithId.get();
            ticketToUpdate.setTitle(ticket.getTitle());
            ticketToUpdate.setDescription(ticket.getDescription());
            ticketToUpdate.setStatus(ticket.getStatus());
            ticketToUpdate.setAffectedTo(ticket.getAffectedTo());
            ticketRepository.save(ticketToUpdate);
        }
        else
        {
            throw new TicketException(TicketException.NotFoundException(id));
        }
    }

    @Override
    public List<TicketDTO> getTicketByUser(Long idUser) {
        List<Ticket> tickets = ticketRepository.findTicketByIdUser(idUser);
        List<TicketDTO> ticketsDto= new ArrayList<>();
        if (tickets.size() > 0) {
            for (Ticket ticket : tickets) {
                TicketDTO dto = TicketMapper.mapToTicketDto(ticket);
                UserDTO userDto = userFeignClient.getUser(dto.getIdUser()).getBody();
                dto.setUser(userDto);
                UserDTO affecteToDto = userFeignClient.getUser(dto.getAffectedTo()).getBody();
                dto.setUserAffectedTo(affecteToDto);
               // ProjectDTO project = projectFeignClient.getProject(dto.getIdProject()).getBody();
              //  dto.setProject(project);
                ticketsDto.add(dto);
            }
            return ticketsDto;
        }else {
            return new ArrayList<TicketDTO>();
        }
    }

    @Override
    public List<TicketDTO> getTicketByAffectedTo(Long affectedTO) {
        List<Ticket> tickets = ticketRepository.findTicketByAffectedTo(affectedTO);
        List<TicketDTO> ticketsDto= new ArrayList<>();
        if (tickets.size() > 0) {
            for (Ticket ticket : tickets) {
                TicketDTO dto = TicketMapper.mapToTicketDto(ticket);
                UserDTO userDto = userFeignClient.getUser(dto.getIdUser()).getBody();
                dto.setUser(userDto);
                UserDTO affecteToDto = userFeignClient.getUser(dto.getAffectedTo()).getBody();
                dto.setUserAffectedTo(affecteToDto);
               // ProjectDTO project = projectFeignClient.getProject(dto.getIdProject()).getBody();
              //  dto.setProject(project);
                ticketsDto.add(dto);
            }
            return ticketsDto;
        }else {
            return new ArrayList<TicketDTO>();
        }
    }

    @Override
    public List<TicketDTO> getTicketByProject(Long idProject) {
        List<Ticket> tickets = ticketRepository.findTicketByIdProject(idProject);
        List<TicketDTO> ticketsDto= new ArrayList<>();
        if (tickets.size() > 0) {
            for (Ticket ticket : tickets) {
                TicketDTO dto = TicketMapper.mapToTicketDto(ticket);
                UserDTO userDto = userFeignClient.getUser(dto.getIdUser()).getBody();
                dto.setUser(userDto);
                UserDTO affecteToDto = userFeignClient.getUser(dto.getAffectedTo()).getBody();
                dto.setUserAffectedTo(affecteToDto);
               // ProjectDTO project = projectFeignClient.getProject(dto.getIdProject()).getBody();
              //  dto.setProject(project);
                ticketsDto.add(dto);
            }
            return ticketsDto;
        }else {
            return new ArrayList<TicketDTO>();
        }
    }
}
