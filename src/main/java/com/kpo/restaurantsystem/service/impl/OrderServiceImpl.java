package com.kpo.restaurantsystem.service.impl;

import com.kpo.restaurantsystem.data.RepoOrder;
import com.kpo.restaurantsystem.enumer.EnumOrder;
import com.kpo.restaurantsystem.enumer.EnumOrderStatus;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceDish;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceOrder;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServicePerson;
import com.kpo.restaurantsystem.web.dto.DtoFixOrder;
import com.kpo.restaurantsystem.web.dto.DtoOrder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements InterfaceServiceOrder {
    private final RepoOrder orderRepository;
    private final InterfaceServicePerson personService;
    private final InterfaceServiceDish dishService;

    @Transactional
    @Override
    public EnumOrder make(DtoOrder dto, String username) {
        var guest = personService.getByLogin(username);
        var dishAmountList = dishService.getOrders(dto.getDishes());
        EnumOrder order = new EnumOrder();
        order.setClientId(guest.getId());
        order.setDishAmountList(dishAmountList);
        order.setDateTime(LocalDateTime.now());
        order.setOrderStatus(EnumOrderStatus.CREATED);
        return save(order);
    }

    @SneakyThrows
    @Override
    public EnumOrder status(EnumOrder order) {
        order.setOrderStatus(EnumOrderStatus.IN_PROGRESS);
        save(order);
        var dishAmountList = order.getDishAmountList();
        int begin = 0;
        while (begin != dishAmountList.size()) {
            var amount = dishAmountList.get(begin);
            var dish = amount.getDish();
            for (int i = 0; i < amount.getAmount(); i++) {
                System.out.println("\n\nCooking...\n\n");
                Thread.sleep(dish.getTime());
                var updatedOrder = get(order.getId());
                if (updatedOrder.getOrderStatus() == EnumOrderStatus.CANCELED) {
                    return updatedOrder;
                }
            }
            dishAmountList = get(order.getId()).getDishAmountList();
            System.out.println(dishAmountList.size());
            ++begin;
        }
        order = get(order.getId());
        order.setOrderStatus(EnumOrderStatus.COMPLETED);
        return save(order);
    }


    @Transactional
    @Override
    public EnumOrder upload(Long orderId, String username, DtoFixOrder dto) {
        var orderToUpdate = get(orderId);
        var client = personService.getByLogin(username);
        if (!Objects.equals(orderToUpdate.getClientId(), client.getId())) {
            throw new IllegalArgumentException("you do not have the rights to this order");
        }
        if (orderToUpdate.getOrderStatus().ordinal() > EnumOrderStatus.IN_PROGRESS.ordinal()) {
            throw new IllegalStateException("order can not be updated");
        }
        var newDishAmountList = dishService.getOrders(dto.getDishes());
        var dishAmounts = orderToUpdate.getDishAmountList();
        dishAmounts.addAll(newDishAmountList);
        return save(orderToUpdate);
    }

    @Transactional
    @Override
    public EnumOrder cancel(Long id, String username) {
        var order = get(id);
        var client = personService.getByLogin(username);
        if (!Objects.equals(order.getClientId(), client.getId())) {
            throw new IllegalArgumentException("you do not have the rights to this order");
        }
        if (order.getOrderStatus().ordinal() > EnumOrderStatus.IN_PROGRESS.ordinal()) {
            throw new IllegalStateException("order can not be canceled");
        }
        order.setOrderStatus(EnumOrderStatus.CANCELED);
        return save(order);
    }

    @Override
    public synchronized EnumOrder get(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("order %d not found", id)));
    }

    @Override
    public EnumOrderStatus getStatus(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("order not found")).getOrderStatus();
    }

    @Transactional
    @Override
    public EnumOrder payOrder(Long id, String username) {
        var order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("order not found"));
        var client = personService.getByLogin(username);
        if (!Objects.equals(order.getClientId(), client.getId())) {
            throw new IllegalArgumentException("You do not have permission to access this order.");
        }
        if (order.getOrderStatus() != EnumOrderStatus.COMPLETED) {
            throw new IllegalStateException("The order cannot be paid.");
        }
        order.setOrderStatus(EnumOrderStatus.PAID);
        return orderRepository.save(order);
    }

    @Override
    public List<EnumOrder> getAllByStatus(EnumOrderStatus status) {
        var result = orderRepository.findAllByOrderStatus(status);
        if (result == null) {
            throw new IllegalStateException("Orders with this status do not exist.");
        }
        return result;
    }

    @Override
    public List<EnumOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    private synchronized EnumOrder save(EnumOrder order) {
        return orderRepository.save(order);
    }
}
