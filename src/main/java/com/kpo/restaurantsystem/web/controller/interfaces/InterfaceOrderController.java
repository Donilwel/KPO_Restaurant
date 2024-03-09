package com.kpo.restaurantsystem.web.controller.interfaces;

import com.kpo.restaurantsystem.enumer.EnumOrder;
import com.kpo.restaurantsystem.web.dto.DtoFixOrder;
import com.kpo.restaurantsystem.web.dto.DtoOrder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

public interface InterfaceOrderController {
    ResponseEntity<EnumOrder> create(@Valid @RequestBody DtoOrder dtoOrder);

    ResponseEntity<EnumOrder> update(@PathVariable Long aLong, @Valid @RequestBody DtoFixOrder dtoFixOrder);

    ResponseEntity<EnumOrder> cancel(@PathVariable Long aLong);

    ResponseEntity<EnumOrder> pay(@PathVariable Long aLong);

    ResponseEntity<BigDecimal> getRevenue();
}
