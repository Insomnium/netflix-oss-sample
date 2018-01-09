package net.ins.hw.netflix.services;

import net.ins.hw.netflix.api.CreditLimitAPIClient;
import net.ins.hw.netflix.domain.Order;
import net.ins.hw.netflix.exceptions.OrderNotCreatedException;
import net.ins.hw.netflix.repository.OrdersRepo;
import lombok.RequiredArgsConstructor;
import net.ins.hw.netflix.exceptions.OrderNotCreatedException;
import net.ins.hw.netflix.repository.OrdersRepo;
import org.springframework.stereotype.Service;

import static net.ins.hw.netflix.domain.CreditLimitStatus.APPROVED;

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
