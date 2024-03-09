package com.kpo.restaurantsystem.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class DtoAuthRequest {
    @NotNull(message = "value must be not null")
    @NotBlank(message = "value must be not blank")
    private String login;
    @NotNull(message = "value must be not null")
    @NotBlank(message = "value must be not blank")
    private String password;
}
