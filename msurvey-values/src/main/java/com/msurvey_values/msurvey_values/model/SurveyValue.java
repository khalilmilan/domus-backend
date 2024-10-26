package com.msurvey_values.msurvey_values.model;

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
public class SurveyValue extends BaseEntity{
    @Id
    @GeneratedValue
    private Long idSurveyValue;
    private String title;
    private String description;
    private String colorCode;
    private Integer status;
    private Long idSurvey;
    private Long idUser;

}
