package com.muser.muser.service;

import com.muser.muser.dto.UserDTO;
import com.muser.muser.exception.UserException;
import com.muser.muser.model.User;
import com.muser.muser.repository.UserRepository;
import com.muser.muser.userMapper.UserMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UserServiceImple implements UserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImple.class);

    private UserRepository userRepository;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = UserMapper.mapToUser(userDTO);
        User savedUser = userRepository.save(user);
        UserDTO savedUserDto = UserMapper.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public List<UserDTO> getALLUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDto= new ArrayList<>();
        if (users.size() > 0) {
            for (User user : users) {
                UserDTO dto =UserMapper.mapToUserDto(user);
                userDto.add(dto);
            }
            return userDto;
        }else {
            return new ArrayList<UserDTO>();
        }
    }

    @Override
    public void deleteUser(Long idUser) throws UserException {
        Optional<User> userOptional = userRepository.findById(idUser);
        if (!userOptional.isPresent()) {
            throw new UserException(UserException.NotFoundException(idUser));
        } else {
            userRepository.deleteById(idUser);
        }
    }

    @Override
    public UserDTO getUser(Long idUser) throws UserException {
        Optional<User> userOptional = userRepository.findById(idUser);
        if (!userOptional.isPresent()) {
            throw new UserException(UserException.NotFoundException(idUser));
        }else {
            UserDTO dto =UserMapper.mapToUserDto(userOptional.get());
            //  List<EventDTO> ambulances = apiClient.getAmbulances(dto.getId());
            // dto.setAmbulances(ambulances);
            return dto;
        }
    }

    @Override
    public void updateUser(Long idUser, User user) throws UserException {
        Optional<User> userWithId = userRepository.findById(idUser);

        if(userWithId.isPresent())
        {
            User userToUpdate=userWithId.get();
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setAdresse(user.getAdresse());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setPhoto(user.getPhoto());
            userToUpdate.setGender(user.getGender());
            userToUpdate.setBirthDate(user.getBirthDate());
            userToUpdate.setPhoneNumber(user.getPhoneNumber());
            userToUpdate.setStatus(user.getStatus());
            userToUpdate.setIdRank(user.getIdRank());
            userRepository.save(userToUpdate);
        }
        else
        {
            throw new UserException(UserException.NotFoundException(idUser));
        }
    }
}
