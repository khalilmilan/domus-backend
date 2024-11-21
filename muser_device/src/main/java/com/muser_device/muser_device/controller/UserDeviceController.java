package com.muser_device.muser_device.controller;

import com.muser_device.muser_device.dto.UserDeviceDTO;
import com.muser_device.muser_device.exception.UserDeviceException;
import com.muser_device.muser_device.model.UserDevice;
import com.muser_device.muser_device.service.UserDeviceService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("user_device")
@AllArgsConstructor
public class UserDeviceController {

    private UserDeviceService userDeviceService;
    @GetMapping(value = "/health/readiness")
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.OK).body("hi");
    }
    @GetMapping(value = "/theni")
    @PreAuthorize("hasAnyAuthority('USER')")
    public String test1(){
        return "hi user";
    }
    @PostMapping
    public ResponseEntity<UserDeviceDTO> saveUserDevice(@RequestBody UserDeviceDTO userDeviceDto){
        UserDeviceDTO savedUserDevice = userDeviceService.saveUserDevice(userDeviceDto);
        return new ResponseEntity<>(savedUserDevice, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllUserDevices() {
        List<UserDeviceDTO> usersDevice = userDeviceService.getALLUserDevice();
        return new ResponseEntity<>(usersDevice, usersDevice.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idUserDevice}")
    public ResponseEntity<?> getUserDevice(@PathVariable("idUserDevice") Long idUserDevice) throws UserDeviceException {
        try {
            return new ResponseEntity<>(userDeviceService.getUserDevice(idUserDevice), HttpStatus.OK);
        } catch (UserDeviceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idUserDevice}")
    public ResponseEntity<?> deleteById(@PathVariable("idUserDevice") Long idUserDevice) throws UserDeviceException{
        try{
            userDeviceService.deleteUserDevice(idUserDevice);
            return new ResponseEntity<>("Successfully deleted groupe with id "+idUserDevice, HttpStatus.OK);
        }
        catch (UserDeviceException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idUserDevice}")
    public ResponseEntity<?> updateById(@PathVariable("idUserDevice") Long idUserDevice, @RequestBody UserDevice userDevice)
    {
        try {
            userDeviceService.updateUserDevice(idUserDevice,userDevice);
            return new ResponseEntity<>("Updated userDevice with id "+idUserDevice+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (UserDeviceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get_device/{idUser}")
    public List<UserDeviceDTO> getDevice(@PathVariable("idUser") Long idUser)  {
            return userDeviceService.getUSerDeviceByIdUser(idUser);
    }
}
