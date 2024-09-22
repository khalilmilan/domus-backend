package com.role.mrole.service;

import com.role.mrole.dto.PermissionDTO;
import com.role.mrole.dto.RoleDTO;
import com.role.mrole.dto.RolePermissionDTO;
import com.role.mrole.dto.UserDTO;
import com.role.mrole.exception.RoleException;
import com.role.mrole.model.Role;
import com.role.mrole.repository.RoleRepository;
import com.role.mrole.roleMapper.RoleMapper;
import com.role.mrole.service.client.RolePermissionFeignClient;
import com.role.mrole.service.client.UserFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class RoleServiceImple implements RoleService{

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImple.class);
    private UserFeignClient userFeignClient;
    private RolePermissionFeignClient rolePermissionFeignClient;
    private RoleRepository roleRepository;
    @Override
    public RoleDTO saveRole(RoleDTO roleDto) {
        Role role = RoleMapper.mapToRole(roleDto);
        Role savedRole = roleRepository.save(role);
        RoleDTO savedRoleDTO = RoleMapper.mapToRoleDto(savedRole);
        return savedRoleDTO;
    }

    @Override
    public List<RoleDTO> getALLRole() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> rolesDto= new ArrayList<>();
        if (roles.size() > 0) {
            for (Role role : roles) {
                RoleDTO dto =RoleMapper.mapToRoleDto(role);
                UserDTO userDto = userFeignClient.getUser(dto.getIdUser()).getBody();
                dto.setUser(userDto);
                rolesDto.add(dto);
            }
            return rolesDto;
        }else {
            return new ArrayList<RoleDTO>();
        }
    }

    @Override
    public void deleteRole(Long idRole) throws RoleException {
        Optional<Role> roleOptional = roleRepository.findById(idRole);
        if (!roleOptional.isPresent()) {
            throw new RoleException(RoleException.NotFoundException(idRole));
        } else {
            roleRepository.deleteById(idRole);
        }
    }

    @Override
    public RoleDTO getRole(Long idRole) throws RoleException {
        Optional<Role> roleOptional = roleRepository.findById(idRole);
        if (!roleOptional.isPresent()) {
            throw new RoleException(RoleException.NotFoundException(idRole));
        }else {
            RoleDTO dto =RoleMapper.mapToRoleDto(roleOptional.get());
            UserDTO userDto = userFeignClient.getUser(dto.getIdUser()).getBody();
            dto.setUser(userDto);
            List<PermissionDTO> permissions =  rolePermissionFeignClient.getPermissionByRole(dto.getIdRole()).getBody();
            dto.setPermissions(permissions);
            return dto;
        }
    }

    @Override
    public void updateRole(Long idRole, Role role) throws RoleException {
        Optional<Role> roleWithId = roleRepository.findById(idRole);
        if(roleWithId.isPresent())
        {
            Role roleToUpdate=roleWithId.get();
            roleToUpdate.setTitle(role.getTitle());
            roleToUpdate.setDescription(role.getDescription());
            roleToUpdate.setStatus(role.getStatus());
            roleToUpdate.setIdUser(role.getIdUser());
            roleRepository.save(roleToUpdate);
        }
        else
        {
            throw new RoleException(RoleException.NotFoundException(idRole));
        }
    }

    @Override
    public void addPermission(Long idRole, Long idPermission) throws RoleException {
        Optional<Role> roleWithId = roleRepository.findById(idRole);
        if(roleWithId.isPresent()) {
            RolePermissionDTO rolePermissionDto = new RolePermissionDTO(
                    idRole,
                    idPermission,
                    1
            );
            rolePermissionFeignClient.saveRolePermission(rolePermissionDto);
        }else{
            throw new RoleException(RoleException.NotFoundException(idRole));
        }
    }

    @Override
    public String removePermission(Long idRole, Long idPermission) throws RoleException {
        Optional<Role> roleWithId = roleRepository.findById(idRole);
        if(roleWithId.isPresent()) {

            return rolePermissionFeignClient.deleteByGroupeAndUser(idRole,idPermission).getBody();
        }else{
            throw new RoleException(RoleException.NotFoundException(idRole));
        }
    }
}
