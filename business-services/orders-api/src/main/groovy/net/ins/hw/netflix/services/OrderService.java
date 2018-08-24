package net.ins.hw.netflix.services;

import lombok.RequiredArgsConstructor;
import net.ins.hw.netflix.api.CreditLimitAPIClient;
import net.ins.hw.netflix.domain.CreditLimitStatus;
import net.ins.hw.netflix.domain.Order;
import net.ins.hw.netflix.domain.OrderStatus;
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
        CreditLimitStatus status = limitAPIClient.getCreditLimit(order.getCardNumber(), order.getAmount()).getStatus();
        if (status == APPROVED) {
            return ordersRepo.save(order.setStatus(OrderStatus.PAID));
        }
        ordersRepo.save(order.setStatus(OrderStatus.REFUSED));
        throw new OrderNotCreatedException("Credit limit exceeded");
    }
}
