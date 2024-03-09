package com.kpo.restaurantsystem.web.dto;

import com.kpo.restaurantsystem.enumer.EnumRole;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class DtoPerson {
    @NotNull(message = "value must be not null")
    private String login;
    @NotNull(message = "value must be not null")
    private String password;
    @NotNull(message = "value confirmation must be not null")
    private String passwordConfirmation;
    @Hidden
    private EnumRole role;
}
