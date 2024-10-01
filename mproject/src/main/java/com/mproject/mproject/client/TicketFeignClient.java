package com.mproject.mproject.client;

import com.mproject.mproject.configurations.FeignClientSecurityConfig;
import com.mproject.mproject.dto.TicketDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-ticket", path = "/ticket",configuration = FeignClientSecurityConfig.class)
public interface TicketFeignClient {
    @GetMapping("/by_project/{idProject}")
    List<TicketDTO> getTicketByProject(@PathVariable("idProject") Long idProject);
}
