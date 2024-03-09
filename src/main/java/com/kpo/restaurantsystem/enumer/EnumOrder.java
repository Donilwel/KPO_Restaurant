package com.kpo.restaurantsystem.enumer;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class EnumOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EnumOrderStatus orderStatus;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "dish_amount_id"))
    private List<EnumDishAmount> dishAmountList;
    private Long clientId;
    private LocalDateTime dateTime;
}
