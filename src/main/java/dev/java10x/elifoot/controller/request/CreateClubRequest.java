package dev.java10x.elifoot.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateClubRequest {

    @NotBlank
    private String name;
    @NotNull
    private LocalDate founded;
    private String urlImg;
    private Long stadiumId;
}
