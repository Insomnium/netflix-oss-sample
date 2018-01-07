package com.epam.hw.netflix.controllers;

import com.epam.hw.netflix.api.CreditLimitAPIClient;
import com.epam.hw.netflix.domain.Amount;
import com.epam.hw.netflix.domain.CreditLimit;
import com.epam.hw.netflix.services.CreditLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class CreditLimitAPIClientController implements CreditLimitAPIClient {

    @Autowired
    private CreditLimitService creditLimitService;

    @Override
    public CreditLimit getCreditLimit(String cardNumber, Amount amount) {
        log.info("Instance {} received credit limit request", this);
        return creditLimitService.checkLimit(cardNumber, amount);
    }
}
