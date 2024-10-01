package com.mrole_permission.mrole_permission.service;

import com.mrole_permission.mrole_permission.client.PermissionFeignClient;
import com.mrole_permission.mrole_permission.dto.PermissionDTO;
import com.mrole_permission.mrole_permission.dto.RolePermissionDTO;
import com.mrole_permission.mrole_permission.exception.RolePermissionException;
import com.mrole_permission.mrole_permission.mapper.RolePermissionMapper;
import com.mrole_permission.mrole_permission.model.RolePermission;
import com.mrole_permission.mrole_permission.repository.RolePermissionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class RolePermissionImple implements RolePermissionService{

    private static final Logger LOGGER = LoggerFactory.getLogger(RolePermissionImple.class);
    private RolePermissionRepository rolePermissionRepository;
    private PermissionFeignClient permissionFeignClient;

    @Override
    public RolePermissionDTO saveRolePermission(RolePermissionDTO rolePermissionDto) {
        RolePermission rolePermission = RolePermissionMapper.mapToRolePermission(rolePermissionDto);
        RolePermission savedRolePermission = rolePermissionRepository.save(rolePermission);
        RolePermissionDTO savedRolePermissionDto = RolePermissionMapper.mapToRolePermissionDTO(savedRolePermission);
        return savedRolePermissionDto;
    }

    @Override
    public List<RolePermissionDTO> getALLRolePermission() {
        List<RolePermission> rolePermission = rolePermissionRepository.findAll();
        List<RolePermissionDTO> RolePermissionDto= new ArrayList<>();
        if (rolePermission.size() > 0) {
            for (RolePermission role : rolePermission) {
                RolePermissionDTO dto =RolePermissionMapper.mapToRolePermissionDTO(role);
                RolePermissionDto.add(dto);
            }
            return RolePermissionDto;
        }else {
            return new ArrayList<RolePermissionDTO>();
        }
    }

    @Override
    public void deleteRolePermission(Long idRolePermission) throws RolePermissionException {
        Optional<RolePermission> rolePermissionOptional = rolePermissionRepository.findById(idRolePermission);
        if (!rolePermissionOptional.isPresent()) {
            throw new RolePermissionException(RolePermissionException.NotFoundException(idRolePermission));
        } else {
            rolePermissionRepository.deleteById(idRolePermission);
        }
    }

    @Override
    public RolePermissionDTO getRolePermission(Long idRolePermission) throws RolePermissionException {
        Optional<RolePermission> rolePermissionOptional = rolePermissionRepository.findById(idRolePermission);
        if (!rolePermissionOptional.isPresent()) {
            throw new RolePermissionException(RolePermissionException.NotFoundException(idRolePermission));
        }else {
            RolePermissionDTO dto =RolePermissionMapper.mapToRolePermissionDTO(rolePermissionOptional.get());
            return dto;
        }
    }

    @Override
    public void updateRolePermission(Long id, RolePermission rolePermission) throws RolePermissionException {
        Optional<RolePermission> rolePermissionWithId = rolePermissionRepository.findById(id);
        if(rolePermissionWithId.isPresent())
        {
            RolePermission rolePermissionToUpdate=rolePermissionWithId.get();
            rolePermissionToUpdate.setIdRole(rolePermission.getIdRole());
            rolePermissionToUpdate.setIdPermission(rolePermission.getIdPermission());
            rolePermissionToUpdate.setStatus(rolePermission.getStatus());
            rolePermissionRepository.save(rolePermissionToUpdate);
        }
        else
        {
            throw new RolePermissionException(RolePermissionException.NotFoundException(id));
        }
    }

    @Override
    public List<PermissionDTO> getRolePermissionByRole(Long idRole) {
        List<RolePermission> rolePermissions = rolePermissionRepository.findRolePermissionByIdRole(idRole);
        List<PermissionDTO> permissions = new ArrayList<>();
        if (rolePermissions.size() > 0) {
            for (RolePermission rolePermission : rolePermissions) {
                PermissionDTO userDto =  permissionFeignClient.getPermission(rolePermission.getIdPermission());
                permissions.add(userDto);
            }
            return permissions;
        }else {
            return new ArrayList<PermissionDTO>();
        }
    }

    @Override
    public void deleteRolePermissionByIdRoleIdPermission(Long idRole, Long idPermission) throws RolePermissionException {
        Optional<RolePermission> rolePermissionWithId = Optional.ofNullable(rolePermissionRepository.findRolePermissionByIdRoleAndIdPermission(idRole,idPermission));
        if(rolePermissionWithId.isPresent())
        {

            rolePermissionRepository.deleteById(rolePermissionWithId.get().getIdRolePermission());
        }
        else
        {
            throw new RolePermissionException(RolePermissionException.NotFoundException(idRole));
        }
    }
}
