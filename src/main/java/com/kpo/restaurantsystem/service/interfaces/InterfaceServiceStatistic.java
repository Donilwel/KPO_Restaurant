package com.kpo.restaurantsystem.service.interfaces;

import com.kpo.restaurantsystem.enumer.EnumDish;

import java.math.BigDecimal;
import java.util.List;

public interface InterfaceServiceStatistic {
    Integer getAmountOfOrdersAtPeriod();

    BigDecimal getAverageGradeOfDish();

    List<EnumDish> mostPopularDishes();
}
