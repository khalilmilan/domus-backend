package com.rank.mrank.service;

import com.rank.mrank.dto.RankDTO;
import com.rank.mrank.exception.RankException;
import com.rank.mrank.model.Rank;

import java.util.List;

public interface RankService {

    RankDTO saveRank(RankDTO rankDto);

    List<RankDTO> getALLRank();

    void deleteRank(Long idRank) throws RankException;

    RankDTO getRank(Long idRank) throws RankException;

    public void updateRank(Long idRank, Rank rank) throws RankException;
}
