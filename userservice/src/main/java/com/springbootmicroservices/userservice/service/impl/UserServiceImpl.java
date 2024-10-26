package com.springbootmicroservices.userservice.service.impl;

import com.springbootmicroservices.userservice.client.EventServiceClient;
import com.springbootmicroservices.userservice.client.ProjectServiceClient;
import com.springbootmicroservices.userservice.client.SurveyServiceClient;
import com.springbootmicroservices.userservice.exception.UserNotFoundException;
import com.springbootmicroservices.userservice.model.SimpleUserDTO;
import com.springbootmicroservices.userservice.model.UserDTO;
import com.springbootmicroservices.userservice.model.UserDetailsDTO;
import com.springbootmicroservices.userservice.model.user.User;
import com.springbootmicroservices.userservice.model.user.entity.UserEntity;
import com.springbootmicroservices.userservice.model.user.mapper.UserEntityToUserMapper;
import com.springbootmicroservices.userservice.model.user.mapper.UserMapper;
import com.springbootmicroservices.userservice.repository.UserRepository;
import com.springbootmicroservices.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();
    private SurveyServiceClient surveyServiceClient;
    private ProjectServiceClient projectServiceClient;
    private EventServiceClient eventServiceClient;
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<UserDTO> getALLUser() {
        return null;
    }

    @Override
    public void deleteUser(Long idUser) {

    }

    @Override
    public User getUser(Long idUser) {
        Optional<UserEntity> userOptional = userRepository.findById(idUser);
        if (userOptional ==null) {
            return null;
        }else {
            return userEntityToUserMapper.map(userOptional.get());
        }
    }

    @Override
    public SimpleUserDTO getSimpleUser(Long idUser) {
        Optional<UserEntity> userOptional = userRepository.findById(idUser);
        if (userOptional ==null) {
            return null;
        }else {
            return UserMapper.mapToSimpleDTO(userOptional.get());
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<UserEntity> userOptional = userRepository.findUserEntityByEmail(email);
        if (userOptional ==null) {
           return null;
        }else {

            return userEntityToUserMapper.map(userOptional.get());
        }
    }

    @Override
    public List<SimpleUserDTO> getAllSimpleUser() {
        List<UserEntity> usersOptional = userRepository.findAll();
        List<SimpleUserDTO> simpleUsers = new ArrayList<SimpleUserDTO>();
        if (usersOptional.size()> 0) {
            for(UserEntity user:usersOptional){
                simpleUsers.add(UserMapper.mapToSimpleDTO(user));
            }
            return simpleUsers;
        }else {
            return new ArrayList<>();
        }
    }

    @Override
    public UserDetailsDTO getUserDetails(Long idUser) {
        Optional<UserEntity> userOptional = userRepository.findById(idUser);
        if (userOptional ==null) {
            return null;
        }else {
            UserDetailsDTO userDetails = new UserDetailsDTO();
            userDetails.setUser(userEntityToUserMapper.map(userOptional.get()));
            int countSurvey = surveyServiceClient.getSurveyCountByUser(idUser);
            userDetails.setCountSurvey(countSurvey);
            int countProject = projectServiceClient.getProjectCountByUser(idUser);
            userDetails.setCountProject(countProject);
            int countEvent = eventServiceClient.count_event_by_user(idUser);
            userDetails.setCountEvent(countEvent);
            return userDetails;
        }
    }

    @Override
    public void updateUser(Long idUser, User user)  {
        Optional<UserEntity> userWithId = userRepository.findById(idUser);

        if(userWithId.isPresent())
        {
            UserEntity userToUpdate=userWithId.get();
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setAdresse(user.getAdresse());
            userToUpdate.setPhoto(user.getPhoto());
            userToUpdate.setGender(user.getGender());
            userToUpdate.setBirthDate(user.getBirthDate());
            userToUpdate.setPhoneNumber(user.getPhoneNumber());
            userToUpdate.setUserStatus(user.getUserStatus());
            userToUpdate.setIdRank(user.getIdRank());
            userRepository.save(userToUpdate);
        }
        else
        {
            throw new UserNotFoundException();
        }
    }
}
