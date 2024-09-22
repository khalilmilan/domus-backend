package com.mrole_permission.mrole_permission.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission extends BaseEntity{

    @Id
    @GeneratedValue
    private Long idRolePermission;
    private Long idRole;
    private Long idPermission;
    private Integer status;
}
