package com.epam.hw.netflix.repository;


import com.epam.hw.netflix.domain.CreditLimit;
import com.epam.hw.netflix.exceptions.CreditLimitNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class DummyCreditLimitRepo implements CreditLimitRepo {

    private final DummyCreditLimits creditLimits;

    @Override
    public Map<String, CreditLimit> getAll() {
        return creditLimits.getDummy();
    }

    @Override
    public CreditLimit findById(String cardNumber) {
        return ofNullable(creditLimits.getDummy()
                .get(cardNumber))
                .orElseThrow(() -> new CreditLimitNotFound(format("No credit limit found for card with number: %s", cardNumber)));
    }
}
