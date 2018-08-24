package net.ins.hw.netflix.repository

import net.ins.hw.netflix.domain.Order
import net.ins.hw.netflix.dummy.DummyOrders
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class OrdersRepo {

    @Autowired
    DummyOrders dummySource;

    Order save(Order order) {
        dummySource.orders << order.with {
            id = UUID.randomUUID()
            it
        }
        order
    }

    List<Order> findAll() {
        dummySource.orders
    }

    void deleteAll() {
        dummySource.orders = []
    }

    long count() {
        dummySource.orders.size()
    }
}