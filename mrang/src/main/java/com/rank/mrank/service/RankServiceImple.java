package com.rank.mrank.service;

import com.rank.mrank.dto.RankDTO;
import com.rank.mrank.exception.RankException;
import com.rank.mrank.model.Rank;
import com.rank.mrank.rankMapper.RankMapper;
import com.rank.mrank.repository.RankRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RankServiceImple implements RankService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RankServiceImple.class);

    private RankRepository rankRepository;

    @Override
    public RankDTO saveRank(RankDTO rankDto) {
        Rank rank = RankMapper.mapToRank(rankDto);
        Rank savedRank = rankRepository.save(rank);
        RankDTO savedRankDto = RankMapper.mapToRankDto(savedRank);
        return savedRankDto;
    }

    @Override
    public List<RankDTO> getALLRank() {
        List<Rank> ranks = rankRepository.findAll();
        List<RankDTO> rankDto= new ArrayList<>();
        if (ranks.size() > 0) {
            for (Rank rank : ranks) {
                RankDTO dto =RankMapper.mapToRankDto(rank);
                rankDto.add(dto);
            }
            return rankDto;
        }else {
            return new ArrayList<RankDTO>();
        }
    }

    @Override
    public void deleteRank(Long idRank) throws RankException {
        Optional<Rank> rankOptional = rankRepository.findById(idRank);
        if (!rankOptional.isPresent()) {
            throw new RankException(RankException.NotFoundException(idRank));
        } else {
            rankRepository.deleteById(idRank);
        }
    }

    @Override
    public RankDTO getRank(Long idRank) throws RankException {
        Optional<Rank> rankOptional = rankRepository.findById(idRank);
        if (!rankOptional.isPresent()) {
            throw new RankException(RankException.NotFoundException(idRank));
        }else {
            RankDTO dto = RankMapper.mapToRankDto(rankOptional.get());

            return dto;
        }
    }

    @Override
    public void updateRank(Long idRank, Rank rank) throws RankException {
        Optional<Rank> rankWithId = rankRepository.findById(idRank);
        if(rankWithId.isPresent())
        {
            Rank rankToUpdate=rankWithId.get();
            rankToUpdate.setIdUser(rank.getIdUser());
            rankToUpdate.setRank(rank.getRank());
            rankToUpdate.setStatus(rank.getStatus());
            rankRepository.save(rankToUpdate);
        }
        else
        {
            throw new RankException(RankException.NotFoundException(idRank));
        }
    }
}
