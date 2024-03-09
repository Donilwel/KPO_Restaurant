package com.kpo.restaurantsystem.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Data
@Validated
public class DtoOrder {
    @NotNull(message = "value must be not null")
    private Map<Long, Integer> dishes;
}
