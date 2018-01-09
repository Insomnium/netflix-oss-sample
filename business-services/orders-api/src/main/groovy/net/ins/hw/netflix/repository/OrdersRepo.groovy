package net.ins.hw.netflix.repository

import net.ins.hw.netflix.domain.Order
import org.springframework.data.mongodb.repository.MongoRepository


interface OrdersRepo extends MongoRepository<Order, String> {

}