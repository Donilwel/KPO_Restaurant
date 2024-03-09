package com.kpo.restaurantsystem.data;

import com.kpo.restaurantsystem.enumer.EnumDishAmount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepoDishAmount extends JpaRepository<EnumDishAmount, Long> {
    Optional<EnumDishAmount> findByDishId(Long dishId);
}
