package com.kpo.restaurantsystem.service.interfaces;

import com.kpo.restaurantsystem.enumer.EnumFeedback;
import com.kpo.restaurantsystem.web.dto.DtoFeedback;

import java.util.List;

public interface InterfaceServiceFeedback {
    EnumFeedback make(Long dishId, DtoFeedback dto);
    List<EnumFeedback> getByAuthorId(Long authorId);
    List<EnumFeedback> getByDishId(Long dishId);
    List<EnumFeedback> getAll();
}
