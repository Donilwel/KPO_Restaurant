package com.kpo.restaurantsystem.web.dto;

import com.kpo.restaurantsystem.enumer.EnumGrade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class DtoFeedback {
    @NotNull(message = "value must be not null")
    private EnumGrade grade;
    @NotNull(message = "value must be not null")
    @NotBlank(message = "value must be not blank")
    private String comment;
}
