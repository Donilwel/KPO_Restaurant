package com.kpo.restaurantsystem.service.interfaces;

import com.kpo.restaurantsystem.enumer.EnumDish;
import com.kpo.restaurantsystem.enumer.EnumDishAmount;
import com.kpo.restaurantsystem.web.dto.DtoDish;

import java.util.List;
import java.util.Map;

public interface InterfaceServiceDish {
    EnumDish make(DtoDish dtoDish);

    EnumDish upload(Long id, DtoDish dtoDish);

    List<EnumDish> getMenu();

    EnumDish updateStatus(Long dishId, boolean menuStatus);

    EnumDish get(Long id);

    List<EnumDishAmount> getOrders(final Map<Long, Integer> dto);

    boolean isExists(Long id, Integer amount);
}
