package com.kpo.restaurantsystem.data;

import com.kpo.restaurantsystem.enumer.EnumDish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoDish extends JpaRepository<EnumDish, Long> {
    List<EnumDish> findAll();
    List<EnumDish> findAllByIsInMenu(boolean isInMenu);
}
