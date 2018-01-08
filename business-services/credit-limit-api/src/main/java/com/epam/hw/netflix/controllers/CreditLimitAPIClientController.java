package com.epam.hw.netflix.controllers;

import com.epam.hw.netflix.api.CreditLimitAPIClient;
import com.epam.hw.netflix.domain.Amount;
import com.epam.hw.netflix.domain.CreditLimit;
import com.epam.hw.netflix.services.CreditLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@Slf4j
public class CreditLimitAPIClientController implements CreditLimitAPIClient {

    @Autowired
    private CreditLimitService creditLimitService;

    @Override
//    @PostMapping(value = LIMIT_BY_CARD_NUMBER_URI, consumes = APPLICATION_JSON_VALUE)
    public CreditLimit getCreditLimit(@PathVariable("cardNumber") String cardNumber, @RequestBody Amount amount) {
        log.info("Instance {} received credit limit request", this);
        return creditLimitService.checkLimit(cardNumber, amount);
    }
}
