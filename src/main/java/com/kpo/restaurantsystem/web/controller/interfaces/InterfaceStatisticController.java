package com.kpo.restaurantsystem.web.controller.interfaces;

import com.kpo.restaurantsystem.enumer.EnumDish;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface InterfaceStatisticController {
    ResponseEntity<Integer> getAmountOfOrders();

    ResponseEntity<BigDecimal> getAverageGradeOfDishes();

    ResponseEntity<List<EnumDish>> getMostPopularDishes();
}
