package com.epam.hw.netflix.api;

import com.epam.hw.netflix.domain.Amount;
import com.epam.hw.netflix.domain.CreditLimit;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@FeignClient(name = "credit-limit-api")
public interface CreditLimitAPIClient {

    // TODO: validation
    @PostMapping(value = "/{cardNumber}", consumes = APPLICATION_JSON_VALUE)
    CreditLimit getCreditLimit(@PathVariable("cardNumber") String cardNumber, @RequestBody Amount amount);
}
