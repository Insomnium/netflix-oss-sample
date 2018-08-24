package net.ins.hw.netflix.controllers;

import lombok.extern.slf4j.Slf4j;
import net.ins.hw.netflix.api.CreditLimitAPIClient;
import net.ins.hw.netflix.domain.Amount;
import net.ins.hw.netflix.domain.CreditLimit;
import net.ins.hw.netflix.services.CreditLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class CreditLimitAPIClientController implements CreditLimitAPIClient {

    @Autowired
    private CreditLimitService creditLimitService;

    @Override
    public CreditLimit getCreditLimit(@PathVariable("cardNumber") String cardNumber, @RequestBody Amount amount) {
        log.info("Instance {} received credit limit request", this);
        return creditLimitService.checkLimit(cardNumber, amount);
    }
}
