package com.epam.hw.netflix.controllers;

import com.epam.hw.netflix.api.CreditLimitAPIClient;
import com.epam.hw.netflix.domain.Amount;
import com.epam.hw.netflix.domain.CreditLimit;
import com.epam.hw.netflix.services.CreditLimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.epam.hw.netflix.controllers.CreditLimitAPIClientController.ROOT_URI;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(ROOT_URI)
@Slf4j
public class CreditLimitAPIClientController implements CreditLimitAPIClient {

    public static final String ROOT_URI = "/";
    public static final String LIMIT_BY_CARD_NUMBER_URI = "/{cardNumber}";

    @Autowired
    private CreditLimitService creditLimitService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Map<String, CreditLimit> getAllCreditLimits() {
        return creditLimitService.getAll();
    }

    @Override
    public CreditLimit getCreditLimit(String cardNumber, Amount amount) {
        log.info("Instance {} received credit limit request", this);
        throw new UnsupportedOperationException("TODO: check limit");
    }
}
