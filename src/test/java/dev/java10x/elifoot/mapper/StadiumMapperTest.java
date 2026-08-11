package dev.java10x.elifoot.mapper;

import dev.java10x.elifoot.controller.request.StadiumRequest;
import dev.java10x.elifoot.controller.response.StadiumResponse;
import dev.java10x.elifoot.entity.Stadium;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class StadiumMapperTest {

    private final StadiumMapper mapper = Mappers.getMapper(StadiumMapper.class);

    @Test
    void toStatiumResponse() {
        // AAA
        // Arrange - Given
        Stadium stadium = Stadium.builder()
                .id(1L)
                .name("Test Stadium")
                .city("Test City")
                .capacity(50000)
                .urlImg("Test URL")
                .build();

        // Action - When
        StadiumResponse stadiumResponse = mapper.toStatiumResponse(stadium);

        // Assertions - Then
        assertNotNull(stadiumResponse);

        assertEquals(stadium.getId(), stadiumResponse.getId());
        assertEquals(stadium.getName(), stadiumResponse.getName());
        assertEquals(stadium.getCity(), stadiumResponse.getCity());
        assertEquals(stadium.getCapacity(), stadiumResponse.getCapacity());
        assertEquals(stadium.getUrlImg(), stadiumResponse.getUrlImg());
    }

    @Test
    void toStatium() {
        // Arrange - Given
        StadiumRequest request = StadiumRequest.builder()
                .name("Test Stadium")
                .city("Test City")
                .capacity(50000)
                .urlImg("Test URL")
                .build();

        // Action - When
        Stadium stadium = mapper.toStatium(request);

        // Assertions - Then
        assertNotNull(stadium);

        assertEquals(request.getName(), stadium.getName());
        assertEquals(request.getCity(), stadium.getCity());
        assertEquals(request.getCapacity(), stadium.getCapacity());
        assertEquals(request.getUrlImg(), stadium.getUrlImg());
    }
}