package com.mevent.mevent.service;

import com.mevent.mevent.dto.EventDetailsDTO;
import com.mevent.mevent.exception.EventException;

public interface IUserService {
    EventDetailsDTO fetchUserDetails(Long EventID) throws EventException ;
}
