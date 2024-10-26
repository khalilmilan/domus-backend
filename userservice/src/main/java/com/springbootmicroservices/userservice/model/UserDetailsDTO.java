package com.springbootmicroservices.userservice.model;

import com.springbootmicroservices.userservice.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {
    User user;
    int countSurvey;
    int countProject;
    int countEvent;
}
