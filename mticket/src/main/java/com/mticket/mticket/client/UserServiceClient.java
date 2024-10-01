package com.mticket.mticket.client;


import com.mticket.mticket.configurations.FeignClientConfig;
import com.mticket.mticket.dto.SimpleUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client interface named {@link UserServiceClient} for interacting with the User Service.
 * Provides methods to validate tokens and retrieve authentication information.
 */
@FeignClient(name = "microservice-user", path = "/user", configuration = FeignClientConfig.class)
public interface UserServiceClient {
    String AUTH_TOKEN = "Authorization";
    /**
     * Validates the provided token by making a request to the user service.
     *
     * @param token the token to validate
     */
    @PostMapping("/validate-token")
    void validateToken(@RequestParam String token);

    /**
     * Retrieves authentication information based on the provided token.
     *
     * @param token the token to use for retrieving authentication information
     * @return {@link UsernamePasswordAuthenticationToken} containing authentication details
     */
    @GetMapping("/authenticate")
    UsernamePasswordAuthenticationToken getAuthentication(@RequestParam String token);

    @GetMapping(value = "/get_simple_user/{idUser}",consumes = "application/json")
    SimpleUserDTO getSimpleUser(@PathVariable Long idUser);

    @GetMapping(value = "/theni")
    void tests();
}
