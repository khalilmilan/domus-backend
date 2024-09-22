package com.rank.mrank.rankMapper;

import com.rank.mrank.dto.RankDTO;
import com.rank.mrank.model.Rank;

public class RankMapper {

    public static RankDTO mapToRankDto(Rank rank){
        RankDTO rankDto = new RankDTO(
                rank.getIdRank(),
                rank.getIdUser(),
                rank.getRank(),
                rank.getStatus()

        );
        return rankDto;
    }

    public static Rank mapToRank(RankDTO rankDto){
        Rank rank = new Rank(
                rankDto.getIdRank(),
                rankDto.getIdUser(),
                rankDto.getRank(),
                rankDto.getStatus()
        );
        return rank;
    }
}
