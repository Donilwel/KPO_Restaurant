package com.kpo.restaurantsystem.service.impl;

import com.kpo.restaurantsystem.enumer.EnumOrder;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceOrderHandler;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceOrder;
import com.kpo.restaurantsystem.web.dto.DtoFixOrder;
import com.kpo.restaurantsystem.web.dto.DtoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class BasedHandlerOrder implements InterfaceServiceOrderHandler {
    private final InterfaceServiceOrder orderService;
    @Autowired
    @Qualifier("orderProcessExecutor")
    private ExecutorService orderProcessExecutor;
    @Autowired
    @Qualifier("addDishAmountExecutor")
    private ExecutorService addDishAmountExecutor;
    @Autowired
    @Qualifier("cancelOrderExecutor")
    private ExecutorService cancelOrderExecutor;

    @Override
    public CompletableFuture<EnumOrder> updateOrder(Long orderId, DtoFixOrder dto) {
        var name = SecurityContextHolder.getContext().getAuthentication().getName();
        return CompletableFuture.supplyAsync(() -> orderService.upload(orderId, name, dto), addDishAmountExecutor);
    }
    @Override
    public CompletableFuture<EnumOrder> handleOrder(DtoOrder dto) {
        var name = SecurityContextHolder.getContext().getAuthentication().getName();
        return CompletableFuture.supplyAsync(() -> orderService.make(dto, name), orderProcessExecutor)
                .thenApply(orderService::status);
    }

    @Override
    public CompletableFuture<EnumOrder> payOrder(Long orderId) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return CompletableFuture.supplyAsync(() -> orderService.payOrder(orderId, username));
    }
    @Override
    public CompletableFuture<EnumOrder> cancelOrder(Long orderId) {
        var name = SecurityContextHolder.getContext().getAuthentication().getName();
        return CompletableFuture.supplyAsync(() -> orderService.cancel(orderId, name), cancelOrderExecutor);
    }
}
