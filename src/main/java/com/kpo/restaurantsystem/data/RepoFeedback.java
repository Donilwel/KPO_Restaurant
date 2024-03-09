package com.kpo.restaurantsystem.data;

import com.kpo.restaurantsystem.enumer.EnumFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoFeedback extends JpaRepository<EnumFeedback, Long> {
    List<EnumFeedback> findAll();

    List<EnumFeedback> findAllByAuthorId(Long authorId);

    List<EnumFeedback> findAllByDishId(Long dishId);
}
