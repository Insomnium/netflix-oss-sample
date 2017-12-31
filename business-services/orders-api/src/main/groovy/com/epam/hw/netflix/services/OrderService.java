package com.epam.hw.netflix.services;

import com.epam.hw.netflix.api.CreditLimitAPIClient;
import com.epam.hw.netflix.domain.Order;
import com.epam.hw.netflix.exceptions.OrderNotCreatedException;
import com.epam.hw.netflix.repository.OrdersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.epam.hw.netflix.domain.CreditLimitStatus.APPROVED;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepo ordersRepo;
    private final CreditLimitAPIClient limitAPIClient;

    public Order createOrder(Order order) {
        if (limitAPIClient.getCreditLimit(order.getCardNumber(), order.getAmount()).getStatus() == APPROVED) {
            return ordersRepo.save(order);
        }
        throw new OrderNotCreatedException("Credit limit exceeded");
    }
}
