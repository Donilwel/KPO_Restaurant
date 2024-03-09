package com.kpo.restaurantsystem.data;

import com.kpo.restaurantsystem.enumer.EnumOrder;
import com.kpo.restaurantsystem.enumer.EnumOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface RepoOrder extends JpaRepository<EnumOrder, Long> {
    void deleteById(@NonNull Long id);

    List<EnumOrder> findAllByOrderStatus(@NonNull EnumOrderStatus status);

    List<EnumOrder> findAll();
}
