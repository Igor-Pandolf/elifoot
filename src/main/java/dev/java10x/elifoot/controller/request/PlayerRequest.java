package dev.java10x.elifoot.controller.request;

import dev.java10x.elifoot.entity.Position;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequest {

    @NotBlank
    private String name;
    @NotNull
    private Position position;
    @NotNull
    private int shirtNumber;
    private String urlImg;
    @NotNull
    private Long clubId;
}
