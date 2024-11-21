package com.mticket.mticket.service;

import com.mticket.mticket.client.NotificationFeignClient;
import com.mticket.mticket.client.UserDeviceFeignClient;
import com.mticket.mticket.client.UserServiceClient;
import com.mticket.mticket.dto.NotificationDTO;
import com.mticket.mticket.dto.SimpleUserDTO;
import com.mticket.mticket.dto.TicketDTO;
import com.mticket.mticket.dto.UserDeviceDTO;
import com.mticket.mticket.exception.TicketException;
import com.mticket.mticket.mapper.TicketMapper;
import com.mticket.mticket.model.Ticket;
import com.mticket.mticket.repository.TicketRepository;
import com.mticket.mticket.client.ProjectFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceImple implements TicketService{
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImple.class);
    private UserServiceClient userFeignClient;
    private TicketRepository ticketRepository;
    private NotificationFeignClient notificationFeignClient;
    private UserDeviceFeignClient userDeviceFeignClient;
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
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                SimpleUserDTO affecteToDto = userFeignClient.getSimpleUser(dto.getAffectedTo());
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
            SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
            dto.setUser(userDto);
            if(null!=dto.getAffectedTo()) {
                SimpleUserDTO affecteToDto = userFeignClient.getSimpleUser(dto.getAffectedTo());
                dto.setUserAffectedTo(affecteToDto);
            }
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
            if(ticketToUpdate.getStatus()!=ticket.getStatus()) {
                List<UserDeviceDTO> devicesOldUser = userDeviceFeignClient.getDevice(ticketToUpdate.getIdUser());
                String ticketStatus="To do";
                if (ticket.getStatus()==2){
                    ticketStatus="In Progress";
                } else if (ticket.getStatus()==3){
                    ticketStatus="Finished";
                }
                for (UserDeviceDTO device:devicesOldUser){
                    NotificationDTO notification = new NotificationDTO();
                    notification.setTitle("📋 Ticket status update ");
                    notification.setDescription("The status of the ticket "+ticketToUpdate.getTitle()+" has been updated to "+ticketStatus);
                    notification.setType(10);
                    notification.setToken(device.getToken());
                    notification.setBadgeCount(1);
                    notification.setIdReciver(ticketToUpdate.getIdUser());
                    notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                    notification.setDate(new Date());
                    notificationFeignClient.saveNotification(notification);
                }
                ticketToUpdate.setStatus(ticket.getStatus());
            }
            if(null!=ticket.getAffectedTo()
                    && ticket.getAffectedTo()!=ticketToUpdate.getAffectedTo()){
               if(null!=ticketToUpdate.getAffectedTo()){
                   List<UserDeviceDTO> devicesOldUser = userDeviceFeignClient.getDevice(ticketToUpdate.getAffectedTo());
                   for (UserDeviceDTO device:devicesOldUser){
                       NotificationDTO notification = new NotificationDTO();
                       notification.setTitle("🚫 Excluded from Ticket");
                       notification.setDescription("you are no longer responsible for the ticket "+ticketToUpdate.getTitle());
                       notification.setType(9);
                       notification.setToken(device.getToken());
                       notification.setBadgeCount(1);
                       notification.setIdReciver(ticketToUpdate.getAffectedTo());
                       notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                       notification.setDate(new Date());
                       notificationFeignClient.saveNotification(notification);
                   }
               }
                List<UserDeviceDTO> devices = userDeviceFeignClient.getDevice(ticket.getAffectedTo());
                for (UserDeviceDTO device:devices){
                    NotificationDTO notification = new NotificationDTO();
                    notification.setTitle("📋 New Project Ticket Created");
                    notification.setDescription("you have been assigned to the ticket "+ticketToUpdate.getTitle());
                    notification.setType(9);
                    notification.setToken(device.getToken());
                    notification.setBadgeCount(1);
                    notification.setIdReciver(ticket.getAffectedTo());
                    notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                    notification.setDate(new Date());
                    notificationFeignClient.saveNotification(notification);
                }
                ticketToUpdate.setAffectedTo(ticket.getAffectedTo());
            }

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
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                if(null!=dto.getAffectedTo()){
                    SimpleUserDTO affecteToDto = userFeignClient.getSimpleUser(dto.getAffectedTo());
                    dto.setUserAffectedTo(affecteToDto);
                }
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
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                SimpleUserDTO affecteToDto = userFeignClient.getSimpleUser(dto.getAffectedTo());
                dto.setUserAffectedTo(affecteToDto);
               // ProjectDTO project = projectFeignClient.getProject(dto.getIdProject());
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
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                if(null!=dto.getAffectedTo()) {
                    SimpleUserDTO affecteToDto = userFeignClient.getSimpleUser(dto.getAffectedTo());
                    dto.setUserAffectedTo(affecteToDto);
                }
                ticketsDto.add(dto);
            }
            return ticketsDto;
        }else {
            return new ArrayList<TicketDTO>();
        }
    }

    @Override
    public List<TicketDTO> getTicketByProjectAndIdUser(Long idProject, Long idUser) {
        return null;
    }
}
