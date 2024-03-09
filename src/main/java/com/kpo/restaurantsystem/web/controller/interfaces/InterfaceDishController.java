package com.kpo.restaurantsystem.web.controller.interfaces;

import com.kpo.restaurantsystem.enumer.EnumDish;
import com.kpo.restaurantsystem.enumer.EnumFeedback;
import com.kpo.restaurantsystem.web.dto.DtoDish;
import com.kpo.restaurantsystem.web.dto.DtoFeedback;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface InterfaceDishController {
    ResponseEntity<EnumDish> create(@Valid @RequestBody DtoDish dtoDish);

    ResponseEntity<EnumDish> update(@PathVariable Long dishId, @Valid @RequestBody DtoDish dtoDish);

    ResponseEntity<List<EnumDish>> getMenu();

    ResponseEntity<EnumDish> addInMenu(@PathVariable Long aLong);

    ResponseEntity<EnumDish> removeFromMenu(@PathVariable Long aLong);

    ResponseEntity<EnumFeedback> createFeedback(@PathVariable Long dishId, @Valid @RequestBody DtoFeedback dtoFeedback);
}
