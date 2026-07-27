package dev.java10x.elifoot.controller.mapper;

import dev.java10x.elifoot.controller.request.StadiumRequest;
import dev.java10x.elifoot.controller.response.StadiumResponse;
import dev.java10x.elifoot.entity.Stadium;

public class StadiumMapper {

    public static StadiumResponse toStatiumResponse(Stadium stadium) {
        return StadiumResponse.builder()
                .id(stadium.getId())
                .name(stadium.getName())
                .city(stadium.getCity())
                .capacity(stadium.getCapacity())
                .urlImg(stadium.getUrlImg())
                .build();
    }

    public static Stadium toStatium(StadiumRequest stadiumRequest) {
        return Stadium.builder()
                .name(stadiumRequest.getName())
                .city(stadiumRequest.getCity())
                .capacity(stadiumRequest.getCapacity())
                .urlImg(stadiumRequest.getUrlImg())
                .build();
    }
}
