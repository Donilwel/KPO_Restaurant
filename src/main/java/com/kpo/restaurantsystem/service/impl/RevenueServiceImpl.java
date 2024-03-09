package com.kpo.restaurantsystem.service.impl;

import com.kpo.restaurantsystem.enumer.EnumOrderStatus;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceOrder;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceRevenue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class RevenueServiceImpl implements InterfaceServiceRevenue {
    private final InterfaceServiceOrder orderService;

    @Override
    public BigDecimal getAmountOfRevenueAtAllTime() {
        var orders = orderService.getAllByStatus(EnumOrderStatus.PAID);
        BigDecimal summ = BigDecimal.ZERO;
        for (var order : orders) {
            BigDecimal sumInOrder = BigDecimal.ZERO;
            for (var dishAmount : order.getDishAmountList()) {
                var dish = dishAmount.getDish();
                var price = dish.getPrice().multiply(BigDecimal.valueOf(dishAmount.getAmount())).setScale(2, RoundingMode.HALF_UP);
                sumInOrder = sumInOrder.add(price).setScale(2, RoundingMode.HALF_UP);
            }
            summ = summ.add(sumInOrder).setScale(2, RoundingMode.HALF_UP);
        }
        return summ;
    }
}
