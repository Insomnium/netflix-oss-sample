package net.ins.hw.netflix.controllers

import net.ins.hw.netflix.api.CreditLimitAPIClient
import net.ins.hw.netflix.domain.Order
import net.ins.hw.netflix.services.OrderService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@RestController
@CompileStatic
@RequestMapping("/")
class OrderController {

    @Autowired
    private OrderService orderService

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    def describeEmployee(@RequestBody Order order) {
        orderService.createOrder(order)
    }
}
