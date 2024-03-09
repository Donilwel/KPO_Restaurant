package com.kpo.restaurantsystem.service.impl;

import com.kpo.restaurantsystem.enumer.EnumDish;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceDish;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceFeedback;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceOrder;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceStatistic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements InterfaceServiceStatistic {
    private final InterfaceServiceFeedback feedbackService;
    private final InterfaceServiceDish dishService;
    private final InterfaceServiceOrder orderService;

    @Override
    public Integer getAmountOfOrdersAtPeriod() {
        return orderService.getAllOrders().size();
    }

    @Override
    public BigDecimal getAverageGradeOfDish() {
        var feedbacks = feedbackService.getAll();
        var sumOfGrades = feedbacks.stream()
                .map(it -> new BigDecimal(it.getGrade().ordinal() + 1))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sumOfGrades.divide(new BigDecimal(feedbacks.size())).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public List<EnumDish> mostPopularDishes() {
        var orders = orderService.getAllOrders();
        Map<Long, Integer> dishMap = new HashMap<>();
        for (var order : orders) {
            for (var dishAmount : order.getDishAmountList()) {
                dishMap.put(
                        dishAmount.getDish().getId(),
                        dishMap.getOrDefault(dishAmount.getDish().getId(), 0)
                                + dishAmount.getAmount()
                );
            }
        }
        var maxValue = dishMap.values().stream()
                .max(Comparator.comparingInt(a -> a))
                .orElseThrow(() -> new IllegalStateException("(most popular dishes) error"));
        List<Long> dishIdList = new ArrayList<>();
        for (var dishAmount : dishMap.entrySet()) {
            if (dishAmount.getValue().equals(maxValue)) {
                dishIdList.add(dishAmount.getKey());
            }
        }
        return dishIdList.stream().map(dishService::get).collect(Collectors.toList());
    }
}
