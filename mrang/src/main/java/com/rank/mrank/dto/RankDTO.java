package com.rank.mrank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RankDTO {

    private Long idRank;

    private Long idUser;
    private Integer rank ;
    private Integer status;
}
