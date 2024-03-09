package com.kpo.restaurantsystem.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoDish {

    @NotNull(message = "value must be not null")
    @NotBlank(message = "value must be not blank")
    private String name;

    @NotNull(message = "value must be not null")
    @Min(value = 1, message = "value must be greater than 1")
    private Integer amount;

    @NotNull(message = "value must be not null")
    @DecimalMin(value = "0.00", message = "value must be greater than 0.00")
    private BigDecimal price;

    @NotNull(message = "value must be not null")
    @Min(value = 1, message = "valur must be greater than 1")
    private Long time;

    @NotNull(message = "value must be not null")
    private Boolean isInMenu;
}
