package com.kpo.restaurantsystem.web.controller;

import com.kpo.restaurantsystem.enumer.EnumFeedback;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceFeedback;
import com.kpo.restaurantsystem.web.controller.interfaces.InterfaceFeedbackController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedbacks")
@RequiredArgsConstructor
@Tag(
        name = "Feedback",
        description = "Getting information about reviews according to requirements"
)
public class FeedBackControllerImpl implements InterfaceFeedbackController {
    private final InterfaceServiceFeedback feedbackService;

    @GetMapping("/author/{aLong}")
    @Operation(summary = "Getting all reviews created by the user with their ID")
    @Override
    public ResponseEntity<List<EnumFeedback>> getAllByAuthor(@PathVariable Long aLong) {
        return ResponseEntity.ok(feedbackService.getByAuthorId(aLong));
    }

    @GetMapping
    @Operation(summary = "Getting all reviews")
    @Override
    public ResponseEntity<List<EnumFeedback>> getAll() {
        return ResponseEntity.ok(feedbackService.getAll());
    }

    @GetMapping("/dish/{aLong}")
    @Operation(summary = "Getting all reviews for a specific dish by its ID")
    @Override
    public ResponseEntity<List<EnumFeedback>> getAllByDish(@PathVariable Long aLong) {
        return ResponseEntity.ok(feedbackService.getByDishId(aLong));
    }
}
