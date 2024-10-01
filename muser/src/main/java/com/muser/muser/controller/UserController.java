package com.muser.muser.controller;

import com.muser.muser.dto.SimpleUserDTO;
import com.muser.muser.dto.UserDTO;
import com.muser.muser.exception.UserException;
import com.muser.muser.model.User;
import com.muser.muser.service.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("user")
@AllArgsConstructor
public class UserController {


    private UserService userService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDto){
        UserDTO savedUser = userService.saveUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllUser() {
        List<UserDTO> users = userService.getALLUser();
        return new ResponseEntity<>(users, users.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("idUser") Long idUser) throws UserException {

        try {
            return new ResponseEntity<>(userService.getUser(idUser), HttpStatus.OK);
        } catch (UserException e) {
          //  return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND).body(new UserDTO());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserDTO());
        }
    }

    @GetMapping("/simple_user/{idUser}")

    public ResponseEntity<SimpleUserDTO> getSimpleUser(@PathVariable("idUser") Long idUser) throws UserException {

        try {
            return new ResponseEntity<>(userService.getSimpleUser(idUser), HttpStatus.OK);
        } catch (UserException e) {
            //  return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND).body(new UserDTO());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SimpleUserDTO());
        }
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> deleteById(@PathVariable("idUser") Long idUser) throws UserException{
        try{
            userService.deleteUser(idUser);
            return new ResponseEntity<>("Successfully deleted user with id "+idUser, HttpStatus.OK);
        }
        catch (UserException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<?> updateById(@PathVariable("idUser") Long idUser, @RequestBody User user)
    {
        try {
            userService.updateUser(idUser,user);
            return new ResponseEntity<>("Updated user with id "+idUser+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (UserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by_email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable("email") String email) throws UserException {
        try {
            return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
        } catch (UserException e) {
            //  return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND).body(new UserDTO());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserDTO());
        }
    }
}
