package com.mproject.mproject.service.client;

import com.mproject.mproject.dto.TicketDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TicketFallback implements TicketFeignClient{
    @Override
    public ResponseEntity<List<TicketDTO>> getTicketByProject(Long idProject) {
        return null;
    }
}
