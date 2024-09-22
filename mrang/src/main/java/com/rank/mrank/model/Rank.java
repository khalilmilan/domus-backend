package com.rank.mrank.model;

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
public class Rank extends BaseEntity{
    @Id
    @GeneratedValue
    private Long idRank;

   private Long idUser;
   private Integer rank ;
   private Integer status;

}
