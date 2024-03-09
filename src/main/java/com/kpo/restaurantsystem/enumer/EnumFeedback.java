package com.kpo.restaurantsystem.enumer;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class EnumFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EnumGrade grade;
    private String comment;
    private Long dishId;
    private Long authorId;
}
