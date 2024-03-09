package com.kpo.restaurantsystem.web.controller;

import com.kpo.restaurantsystem.enumer.EnumDish;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceStatistic;
import com.kpo.restaurantsystem.web.controller.interfaces.InterfaceStatisticController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/statistic/admin")
@RequiredArgsConstructor
@Tag(
        name = "Statistic",
        description = "Getting statistics on orders and dishes"
)
@Validated
public class StatisticControllerImpl implements InterfaceStatisticController {
    private final InterfaceServiceStatistic statisticService;

    @GetMapping("/popular")
    @Operation(summary = "Getting a list of the most popular dishes")
    @Override
    public ResponseEntity<List<EnumDish>> getMostPopularDishes() {
        return ResponseEntity.ok(statisticService.mostPopularDishes());
    }

    @GetMapping("/amount")
    @Operation(summary = "Getting the number of orders for a specified period")
    @Override
    public ResponseEntity<Integer> getAmountOfOrders() {
        return ResponseEntity.ok(statisticService.getAmountOfOrdersAtPeriod());
    }

    @GetMapping("/grade")
    @Operation(summary = "Getting the average rating of dishes")
    @Override
    public ResponseEntity<BigDecimal> getAverageGradeOfDishes() {
        return ResponseEntity.ok(statisticService.getAverageGradeOfDish());
    }
}
