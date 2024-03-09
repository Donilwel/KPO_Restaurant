package com.kpo.restaurantsystem.service.impl;

import com.kpo.restaurantsystem.data.RepoDishAmount;
import com.kpo.restaurantsystem.data.RepoDish;
import com.kpo.restaurantsystem.enumer.EnumDish;
import com.kpo.restaurantsystem.enumer.EnumDishAmount;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceDish;
import com.kpo.restaurantsystem.web.dto.DtoDish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements InterfaceServiceDish {
    private final RepoDish dishRepository;
    private final RepoDishAmount dishAmountRepository;

    @Override
    public EnumDish make(DtoDish dtoDish) {
        EnumDish dish = new EnumDish();
        dish.setName(dtoDish.getName());
        dish.setAmount(dtoDish.getAmount());
        dish.setPrice(dtoDish.getPrice());
        dish.setTime(dtoDish.getTime());
        dish.setIsInMenu(dtoDish.getIsInMenu());
        return dishRepository.save(dish);
    }

    @Override
    public EnumDish upload(Long id, DtoDish dtoDish) {
        var ored = dishRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("dish not found"));
        ored.setIsInMenu(dtoDish.getIsInMenu());
        ored.setName(dtoDish.getName());
        ored.setAmount(dtoDish.getAmount());
        ored.setPrice(dtoDish.getPrice());
        ored.setTime(dtoDish.getTime());
        dishRepository.save(ored);
        return ored;
    }

    @Override
    public List<EnumDish> getMenu() {
        return dishRepository.findAllByIsInMenu(true);
    }

    @Transactional
    @Override
    public synchronized EnumDish updateStatus(Long dishId, boolean menuStatus) {
        var dish = dishRepository.findById(dishId).orElseThrow(() -> new IllegalArgumentException("dish not found"));
        dish.setIsInMenu(menuStatus);

        return dishRepository.save(dish);
    }

    @Override
    public synchronized EnumDish get(Long id) {
        return dishRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("dish not found"));
    }

    @Transactional
    @Override
    public synchronized List<EnumDishAmount> getOrders(final Map<Long, Integer> dto) {
        List<EnumDishAmount> enumDishAmountArrayList = new ArrayList<>();
        for (var dishOrder : dto.entrySet()) {
            var dish = get(dishOrder.getKey());
            if (!dish.getIsInMenu()) {
                throw new IllegalArgumentException("dish is not in order");
            }
            if (dish.getAmount() < dishOrder.getValue()) {
                throw new IllegalStateException("dish amount is not enough");
            }
            dish.setAmount(dish.getAmount() - dishOrder.getValue());
            if (dish.getAmount() == 0) {
                dish.setIsInMenu(false);
            }
            dishRepository.save(dish);

            var dishAmount = new EnumDishAmount();
            dishAmount.setDish(dish);
            dishAmount.setAmount(dishOrder.getValue());

            enumDishAmountArrayList.add(dishAmountRepository.save(dishAmount));
        }

        return enumDishAmountArrayList;
    }


    @Override
    public boolean isExists(Long id, Integer amount) {
        var dish = dishRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("dish not found"));
        return dish.getIsInMenu() && dish.getAmount() >= amount;
    }
}
