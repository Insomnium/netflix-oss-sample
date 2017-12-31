package com.epam.hw.netflix.repository

import com.epam.hw.netflix.domain.Order
import org.springframework.data.mongodb.repository.MongoRepository


interface OrdersRepo extends MongoRepository<Order, String> {

}