package com.kpo.restaurantsystem.service.impl;

import com.kpo.restaurantsystem.data.RepoFeedback;
import com.kpo.restaurantsystem.enumer.EnumFeedback;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceDish;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServicePerson;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceFeedback;
import com.kpo.restaurantsystem.web.dto.DtoFeedback;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements InterfaceServiceFeedback {
    private final InterfaceServicePerson personService;
    private final InterfaceServiceDish dishService;
    private final RepoFeedback feedbackRepository;

    @Transactional
    @Override
    public EnumFeedback make(Long dishId, DtoFeedback dto) {
        var dish = dishService.get(dishId);
        if (dish.getIsInMenu() == false) {
            throw new IllegalStateException("dish is not in menu");
        }
        EnumFeedback feedback = new EnumFeedback();
        feedback.setAuthorId(getAuthorId());
        feedback.setGrade(dto.getGrade());
        feedback.setDishId(dishId);
        feedback.setComment(dto.getComment());
        feedback = feedbackRepository.save(feedback);
        return feedback;
    }

    @Override
    public List<EnumFeedback> getByAuthorId(Long authorId) {
        return feedbackRepository.findAllByAuthorId(authorId);
    }

    @Override
    public List<EnumFeedback> getByDishId(Long dishId) {
        return feedbackRepository.findAllByDishId(dishId);
    }

    @Override
    public List<EnumFeedback> getAll() {
        return feedbackRepository.findAll();
    }

    private Long getAuthorId() {
        var user = SecurityContextHolder.getContext().getAuthentication().getName();
        return personService.getByLogin(user).getId();
    }
}
