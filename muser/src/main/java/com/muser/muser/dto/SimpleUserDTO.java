package com.muser.muser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserDTO {
    private Long idUser;
    private String firstName;
    private String lastName;
    private String photo;
}
