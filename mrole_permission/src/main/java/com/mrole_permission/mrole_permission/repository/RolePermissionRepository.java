package com.mrole_permission.mrole_permission.repository;

import com.mrole_permission.mrole_permission.dto.RolePermissionDTO;
import com.mrole_permission.mrole_permission.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission,Long> {

    List<RolePermission> findRolePermissionByIdRole(Long idGroupe);

    RolePermission findRolePermissionByIdRoleAndIdPermission(Long idGroupe,Long idPermission);
}
