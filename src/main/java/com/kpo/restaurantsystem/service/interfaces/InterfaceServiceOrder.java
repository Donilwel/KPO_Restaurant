package com.kpo.restaurantsystem.service.interfaces;

import com.kpo.restaurantsystem.enumer.EnumOrder;
import com.kpo.restaurantsystem.enumer.EnumOrderStatus;
import com.kpo.restaurantsystem.web.dto.DtoFixOrder;
import com.kpo.restaurantsystem.web.dto.DtoOrder;

import java.util.List;

public interface InterfaceServiceOrder {
    EnumOrder make(DtoOrder dto, String username);

    EnumOrder status(EnumOrder order);

    EnumOrder upload(Long orderId, String username, DtoFixOrder dto);

    EnumOrder cancel(Long id, String username);

    EnumOrder get(Long id);

    EnumOrderStatus getStatus(Long id);

    EnumOrder payOrder(Long id, String username);

    List<EnumOrder> getAllByStatus(EnumOrderStatus status);

    List<EnumOrder> getAllOrders();
}
