package com.mevent.mevent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserDetailsDTO {
    SimpleUserDTO user;
    Integer answer;
}
