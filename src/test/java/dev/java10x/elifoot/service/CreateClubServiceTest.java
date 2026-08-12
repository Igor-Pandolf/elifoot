package dev.java10x.elifoot.service;

import dev.java10x.elifoot.controller.request.CreateClubRequest;
import dev.java10x.elifoot.controller.response.ClubDetailResponse;
import dev.java10x.elifoot.entity.Club;
import dev.java10x.elifoot.entity.Stadium;
import dev.java10x.elifoot.mapper.ClubMapper;
import dev.java10x.elifoot.repository.ClubRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateClubServiceTest {

    @InjectMocks
    CreateClubService createClubService;
    @Mock
    ClubRepository clubRepository;
    @Mock
    ClubMapper clubMapper;
    @Mock
    FindStadiumService findStadiumService;
    @Captor
    ArgumentCaptor<Club> captor;

    @Test
    void shouldCreateNewClubWithStadium() {
        CreateClubRequest request = CreateClubRequest.builder()
                .name("Test Club")
                .founded(LocalDate.of(2020, 1, 1))
                .urlImg("Test URL")
                .stadiumId(1L)
                .build();

        Club club = Club.builder()
                .id(1L)
                .name("Test Club")
                .founded(LocalDate.of(2020, 1, 1))
                .urlImg("Test URL")
                .stadium(Stadium.builder().id(10L).build())
                .build();

        Stadium stadium = Stadium.builder()
                .id(10L)
                .name("Test Stadium")
                .city("Test City")
                .capacity(50000)
                .urlImg("Test URL")
                .build();

        Mockito.when(clubMapper.toClub(request)).thenReturn(club);
        Mockito.when(findStadiumService.findById(stadium.getId())).thenReturn(stadium);

        createClubService.execute(request);

        Mockito.verify(clubMapper).toClub(request);
        Mockito.verify(findStadiumService).findById(stadium.getId());
        Mockito.verify(clubRepository).save(Mockito.any(Club.class));

        Mockito.verify(clubRepository.save(captor.capture()));
        Club savedClub = captor.getValue();

        assertNotNull(savedClub);
        assertEquals(club.getId(), savedClub.getId());
        assertEquals(club.getName(), savedClub.getName());
        assertEquals(club.getFounded(), savedClub.getFounded());
        assertEquals(club.getUrlImg(), savedClub.getUrlImg());
        assertNotNull(savedClub.getStadium());
        assertEquals(stadium.getId(), savedClub.getStadium().getId());
        assertEquals(stadium.getName(), savedClub.getStadium().getName());
        assertEquals(stadium.getCity(), savedClub.getStadium().getCity());
        assertEquals(stadium.getCapacity(), savedClub.getStadium().getCapacity());
    }

    @Test
    void shouldCreateNewClubWithoutStadium() {
        CreateClubRequest request = CreateClubRequest.builder()
                .name("Test Club")
                .founded(LocalDate.of(2020, 1, 1))
                .urlImg("Test URL")
                .stadiumId(1L)
                .build();

        Club club = Club.builder()
                .id(1L)
                .name("Test Club")
                .founded(LocalDate.of(2020, 1, 1))
                .urlImg("Test URL")
                .build();

        Stadium stadium = Stadium.builder()
                .id(10L)
                .name("Test Stadium")
                .city("Test City")
                .capacity(50000)
                .urlImg("Test URL")
                .build();

        Mockito.when(clubMapper.toClub(request)).thenReturn(club);

        createClubService.execute(request);

        Mockito.verify(clubMapper).toClub(request);
        Mockito.verify(findStadiumService, Mockito.never()).findById(stadium.getId());
        Mockito.verify(clubRepository).save(Mockito.any(Club.class));

        Mockito.verify(clubRepository.save(captor.capture()));
        Club savedClub = captor.getValue();

        assertNotNull(savedClub);
        assertEquals(club.getId(), savedClub.getId());
        assertEquals(club.getName(), savedClub.getName());
        assertEquals(club.getFounded(), savedClub.getFounded());
        assertEquals(club.getUrlImg(), savedClub.getUrlImg());
    }
}