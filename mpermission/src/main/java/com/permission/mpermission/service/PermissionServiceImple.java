package com.permission.mpermission.service;

import com.permission.mpermission.client.UserServiceClient;
import com.permission.mpermission.dto.PermissionDTO;
import com.permission.mpermission.dto.SimpleUserDTO;
import com.permission.mpermission.exception.PermissionException;
import com.permission.mpermission.model.Permission;
import com.permission.mpermission.permissionMapper.PermissionMapper;
import com.permission.mpermission.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PermissionServiceImple implements PermissionService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionServiceImple.class);
    private UserServiceClient userFeignClient;
    private PermissionRepository permissionRepository;
    @Override
    public PermissionDTO savePermission(PermissionDTO permissionDto) {
        Permission permission = PermissionMapper.mapToPermission(permissionDto);
        Permission savedPermission = permissionRepository.save(permission);
        PermissionDTO savedPermissionDto = PermissionMapper.mapToPermissionDto(savedPermission);
        return savedPermissionDto;
    }

    @Override
    public List<PermissionDTO> getALLPermission() {
        List<Permission> permissions = permissionRepository.findAll();
        List<PermissionDTO> permissionDto= new ArrayList<>();
        if (permissions.size() > 0) {
            for (Permission permission : permissions) {
                PermissionDTO dto = PermissionMapper.mapToPermissionDto(permission);
                permissionDto.add(dto);
            }
            return permissionDto;
        }else {
            return new ArrayList<PermissionDTO>();
        }
    }

    @Override
    public void deletePermission(Long idPermission) throws PermissionException {
        Optional<Permission> permissionOptional = permissionRepository.findById(idPermission);
        if (!permissionOptional.isPresent()) {
            throw new PermissionException(PermissionException.NotFoundException(idPermission));
        } else {
            permissionRepository.deleteById(idPermission);
        }
    }

    @Override
    public PermissionDTO getPermission(Long idPermission) throws PermissionException {
        Optional<Permission> permissionOptional = permissionRepository.findById(idPermission);
        if (!permissionOptional.isPresent()) {
            throw new PermissionException(PermissionException.NotFoundException(idPermission));
        }else {
            PermissionDTO dto = PermissionMapper.mapToPermissionDto(permissionOptional.get());
            SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
            dto.setUser(userDto);
            return dto;
        }
    }

    @Override
    public void updatePermission(Long idPermission, Permission permission) throws PermissionException {
        Optional<Permission> permissionWithId = permissionRepository.findById(idPermission);
        if(permissionWithId.isPresent())
        {
            Permission permissionToUpdate=permissionWithId.get();
            permissionToUpdate.setTitle(permission.getTitle());
            permissionToUpdate.setDescription(permission.getDescription());
            permissionToUpdate.setStatus(permission.getStatus());
            permissionToUpdate.setIdUser(permission.getIdUser());
            permissionRepository.save(permissionToUpdate);
        }
        else
        {
            throw new PermissionException(PermissionException.NotFoundException(idPermission));
        }
    }
}
