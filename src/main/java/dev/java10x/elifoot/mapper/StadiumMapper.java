package dev.java10x.elifoot.mapper;

import dev.java10x.elifoot.controller.request.StadiumRequest;
import dev.java10x.elifoot.controller.response.StadiumResponse;
import dev.java10x.elifoot.entity.Stadium;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StadiumMapper {

    StadiumResponse toStatiumResponse(Stadium stadium);

    Stadium toStatium(StadiumRequest stadiumRequest);
}
