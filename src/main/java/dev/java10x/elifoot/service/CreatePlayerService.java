package dev.java10x.elifoot.service;

import dev.java10x.elifoot.controller.request.PlayerRequest;
import dev.java10x.elifoot.controller.response.PlayerResponse;
import dev.java10x.elifoot.entity.Player;
import dev.java10x.elifoot.mapper.PlayerMapper;
import dev.java10x.elifoot.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePlayerService {

    private final PlayerRepository playerRepository;
    private final FindClubService findClubService;
    private final PlayerMapper playerMapper;

    public PlayerResponse execute(PlayerRequest playerRequest) {
        Player newPlayer = playerMapper.toPlayer(playerRequest);
        newPlayer.setClub(findClubService.findById(playerRequest.getClubId()));
        Player savedPlayer = playerRepository.save(newPlayer);
        return playerMapper.toPlayerResponse(savedPlayer);
    }
}
