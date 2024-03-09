package com.kpo.restaurantsystem.service.interfaces;

import com.kpo.restaurantsystem.enumer.EnumOrder;
import com.kpo.restaurantsystem.web.dto.DtoFixOrder;
import com.kpo.restaurantsystem.web.dto.DtoOrder;

import java.util.concurrent.CompletableFuture;

public interface InterfaceServiceOrderHandler {
    CompletableFuture<EnumOrder> handleOrder(DtoOrder dto);
    CompletableFuture<EnumOrder> updateOrder(Long orderId, DtoFixOrder dto);
    CompletableFuture<EnumOrder> cancelOrder(Long orderId);
    CompletableFuture<EnumOrder> payOrder(Long orderId);
}
