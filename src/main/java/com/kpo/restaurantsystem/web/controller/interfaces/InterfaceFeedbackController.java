package com.kpo.restaurantsystem.web.controller.interfaces;

import com.kpo.restaurantsystem.enumer.EnumFeedback;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface InterfaceFeedbackController {
    ResponseEntity<List<EnumFeedback>> getAllByAuthor(@PathVariable Long aLong);
    ResponseEntity<List<EnumFeedback>> getAllByDish(@PathVariable Long aLong);
    ResponseEntity<List<EnumFeedback>> getAll();
}
