package dev.java10x.elifoot.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StadiumRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String city;
    private Integer capacity;
    private String urlImg;
}
