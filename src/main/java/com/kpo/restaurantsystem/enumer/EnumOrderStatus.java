package com.kpo.restaurantsystem.enumer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EnumOrderStatus {
    CREATED("CREATED"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    PAID("PAID"),
    CANCELED("CANCELED");
    private final String type;
}
