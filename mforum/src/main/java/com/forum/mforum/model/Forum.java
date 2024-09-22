package com.forum.mforum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forum extends BaseEntity{

    @Id
    @GeneratedValue
    private Long idForum;

    private String title;

    private String description;
    private Integer status;
    private Long idUser;
    private Long idEvent;
}
