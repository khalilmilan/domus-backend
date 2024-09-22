package com.mproject.mproject.service.client;

import com.mproject.mproject.dto.TicketDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-ticket", fallback = TicketFallback.class)
public interface TicketFeignClient {
    @GetMapping("/ticket/by_project/{idProject}")
    ResponseEntity<List<TicketDTO>> getTicketByProject(@PathVariable("idProject") Long idProject);
}
