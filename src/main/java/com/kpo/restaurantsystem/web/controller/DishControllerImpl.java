package com.kpo.restaurantsystem.web.controller;

import com.kpo.restaurantsystem.web.dto.DtoFeedback;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.kpo.restaurantsystem.enumer.EnumDish;
import com.kpo.restaurantsystem.enumer.EnumFeedback;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceDish;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceFeedback;
import com.kpo.restaurantsystem.web.controller.interfaces.InterfaceDishController;
import com.kpo.restaurantsystem.web.dto.DtoDish;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Dish",
        description = "Creating, editing dishes, editing menus, creating reviews for dishes"
)
public class DishControllerImpl implements InterfaceDishController {
    private final InterfaceServiceDish serviceDish;
    private final InterfaceServiceFeedback serviceFeedback;

    @PutMapping("/admin/{dishId}")
    @Operation(summary = "Updating a dish entity by its ID")
    @Override
    public ResponseEntity<EnumDish> update(@PathVariable Long dishId, @Valid @RequestBody DtoDish dtoDish) {
        return ResponseEntity.ok(serviceDish.upload(dishId, dtoDish));
    }

    @PostMapping("/admin")
    @Operation(summary = "Creating a new dish entity")
    @Override
    public ResponseEntity<EnumDish> create(@Valid @RequestBody DtoDish dtoDish) {
        return ResponseEntity.ok(serviceDish.make(dtoDish));
    }

    @GetMapping("/menu")
    @Operation(summary = "Getting a list of all dishes currently on the menu")
    @Override
    public ResponseEntity<List<EnumDish>> getMenu() {
        return ResponseEntity.ok(serviceDish.getMenu());
    }

    @PostMapping("/{dishId}/feedback")
    @Operation(summary = "Creating a review for a dish by its ID")
    @Override
    public ResponseEntity<EnumFeedback> createFeedback(@PathVariable Long dishId, @Valid @RequestBody DtoFeedback dtoFeedback) {
        return ResponseEntity.ok(serviceFeedback.make(dishId, dtoFeedback));
    }

    @PutMapping("/admin/menu/{aLong}/add")
    @Operation(summary = "Adding a dish to the menu by its ID")
    @Override
    public ResponseEntity<EnumDish> addInMenu(@PathVariable Long aLong) {
        return ResponseEntity.ok(serviceDish.updateStatus(aLong, true));
    }

    @PutMapping("/admin/menu/{aLong}/remove")
    @Operation(summary = "Removing a dish from the menu by its ID")
    @Override
    public ResponseEntity<EnumDish> removeFromMenu(@PathVariable Long aLong) {
        return ResponseEntity.ok(serviceDish.updateStatus(aLong, false));
    }
}
