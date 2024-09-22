package com.vote.mvote.service;

import com.vote.mvote.dto.VoteDTO;
import com.vote.mvote.exception.VoteException;
import com.vote.mvote.model.Vote;
import com.vote.mvote.repository.VoteRepository;
import com.vote.mvote.voteMapper.VoteMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class VoteServiceImple implements VoteService{

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteServiceImple.class);

    private VoteRepository voteRepository;
    @Override
    public VoteDTO saveVote(VoteDTO voteDto) {
        Vote vote = VoteMapper.mapToVote(voteDto);
        vote.setCreatedAt(LocalDateTime.now());
        Vote savedVote = voteRepository.save(vote);
        VoteDTO savedVoteDto = VoteMapper.mapToVoteDto(savedVote);
        return savedVoteDto;
    }

    @Override
    public List<VoteDTO> getALLVote() {
        List<Vote> votes = voteRepository.findAll();
        List<VoteDTO> voteDto= new ArrayList<>();
        if (votes.size() > 0) {
            for (Vote vote : votes) {
                VoteDTO dto = VoteMapper.mapToVoteDto(vote);
                voteDto.add(dto);
            }
            return voteDto;
        }else {
            return new ArrayList<VoteDTO>();
        }
    }

    @Override
    public void deleteVote(Long idVote) throws VoteException {
        Optional<Vote> voteOptional = voteRepository.findById(idVote);
        if (!voteOptional.isPresent()) {
            throw new VoteException(VoteException.NotFoundException(idVote));
        } else {
            voteRepository.deleteById(idVote);
        }
    }

    @Override
    public VoteDTO getVote(Long idVote) throws VoteException {
        Optional<Vote> voteOptional = voteRepository.findById(idVote);
        if (!voteOptional.isPresent()) {
            throw new VoteException(VoteException.NotFoundException(idVote));
        }else {
            VoteDTO dto = VoteMapper.mapToVoteDto(voteOptional.get());
            //  List<EventDTO> ambulances = apiClient.getAmbulances(dto.getId());
            // dto.setAmbulances(ambulances);
            return dto;
        }
    }

    @Override
    public void updateVote(Long idVote, Vote vote) throws VoteException {
        Optional<Vote> voteWithId = voteRepository.findById(idVote);
        if(voteWithId.isPresent())
        {
            Vote voteToUpdate=voteWithId.get();
            voteToUpdate.setTitle(vote.getTitle());
            voteToUpdate.setStatus(vote.getStatus());
            voteToUpdate.setIdSurveyValue(vote.getIdSurveyValue());
            voteToUpdate.setUpdatedAt(LocalDateTime.now());
            voteRepository.save(voteToUpdate);
        }
        else
        {
            throw new VoteException(VoteException.NotFoundException(idVote));
        }
    }

    @Override
    public List<VoteDTO> getVoteByUser(Long idUser) {
        List<Vote> votes = voteRepository.findVotesByIdUser(idUser);
        List<VoteDTO> voteDto= new ArrayList<>();
        if (votes.size() > 0) {
            for (Vote vote : votes) {
                VoteDTO dto = VoteMapper.mapToVoteDto(vote);
                voteDto.add(dto);
            }
            return voteDto;
        }else {
            return new ArrayList<VoteDTO>();
        }
    }

    @Override
    public List<VoteDTO> getVoteBySurvey(Long idSurvey) {
        List<Vote> votes = voteRepository.findVotesByIdSurvey(idSurvey);
        List<VoteDTO> voteDto= new ArrayList<>();
        if (votes.size() > 0) {
            for (Vote vote : votes) {
                VoteDTO dto = VoteMapper.mapToVoteDto(vote);
                voteDto.add(dto);
            }
            return voteDto;
        }else {
            return new ArrayList<VoteDTO>();
        }
    }

    @Override
    public List<VoteDTO> getVoteBySurveyAndSurveyValue(Long idSurvey, Long idSurveyValue) {
        List<Vote> votes = voteRepository.findVoteByIdSurveyAndIdSurveyValue(idSurvey,idSurveyValue);
        List<VoteDTO> voteDto= new ArrayList<>();
        if (votes.size() > 0) {
            for (Vote vote : votes) {
                VoteDTO dto = VoteMapper.mapToVoteDto(vote);
                voteDto.add(dto);
            }
            return voteDto;
        }else {
            return new ArrayList<VoteDTO>();
        }
    }
}
