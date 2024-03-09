package com.kpo.restaurantsystem.enumer;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class EnumDishAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private EnumDish dish;
    private Integer amount;
}
