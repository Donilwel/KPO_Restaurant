package com.kpo.restaurantsystem.web.controller;

import com.kpo.restaurantsystem.web.dto.DtoOrder;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceOrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.kpo.restaurantsystem.enumer.EnumOrder;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceRevenue;
import com.kpo.restaurantsystem.web.controller.interfaces.InterfaceOrderController;
import com.kpo.restaurantsystem.web.dto.DtoFixOrder;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Order",
        description = "Creating, updating, cancelling, and paying for orders"
)
public class OrderControllerImpl implements InterfaceOrderController {
    private final InterfaceServiceOrderHandler serviceOrderHandler;
    private final InterfaceServiceRevenue serviceRevenue;
    @PostMapping
    @Operation(summary = "Creating an order")
    @SneakyThrows
    @Override
    public ResponseEntity<EnumOrder> create(@Valid @RequestBody DtoOrder dtoOrder) {
        return ResponseEntity.ok(serviceOrderHandler.handleOrder(dtoOrder).get());
    }

    @GetMapping("/admin/revenue")
    @Operation(summary = "Revenue for all time")
    @Override
    public ResponseEntity<BigDecimal> getRevenue() {
        return ResponseEntity.ok(serviceRevenue.getAmountOfRevenueAtAllTime());
    }

    @PutMapping("/{aLong}")
    @Operation(summary = "Updating the list of dishes for a specific order by its ID")
    @SneakyThrows
    @Override
    public ResponseEntity<EnumOrder> update(@PathVariable Long aLong, @Valid @RequestBody DtoFixOrder dtoFixOrder) {
        return ResponseEntity.ok(serviceOrderHandler.updateOrder(aLong, dtoFixOrder).get());
    }

    @PutMapping("/pay/{aLong}")
    @Operation(summary = "Payment for an order by its ID")
    @SneakyThrows
    @Override
    public ResponseEntity<EnumOrder> pay(@PathVariable Long aLong) {
        return ResponseEntity.ok(serviceOrderHandler.payOrder(aLong).get());
    }

    @PutMapping("/cancel/{aLong}")
    @Operation(summary = "Cancelling an order by its ID")
    @SneakyThrows
    @Override
    public ResponseEntity<EnumOrder> cancel(@PathVariable Long aLong) {
        return ResponseEntity.ok(serviceOrderHandler.cancelOrder(aLong).get());
    }
}
