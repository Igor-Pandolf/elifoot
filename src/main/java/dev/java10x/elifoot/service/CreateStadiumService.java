package dev.java10x.elifoot.service;

import dev.java10x.elifoot.controller.mapper.StadiumMapper;
import dev.java10x.elifoot.controller.request.StadiumRequest;
import dev.java10x.elifoot.controller.response.StadiumResponse;
import dev.java10x.elifoot.entity.Stadium;
import dev.java10x.elifoot.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateStadiumService {

    private final StadiumRepository stadiumRepository;

    public StadiumResponse execute(StadiumRequest request) {
        Stadium savedStadium = stadiumRepository.save(StadiumMapper.toStatium(request));
        return StadiumMapper.toStatiumResponse(savedStadium);
    }
}
