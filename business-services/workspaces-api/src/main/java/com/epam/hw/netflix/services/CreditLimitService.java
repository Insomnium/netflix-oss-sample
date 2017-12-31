package com.epam.hw.netflix.services;

import com.epam.hw.netflix.domain.CreditLimit;
import com.epam.hw.netflix.repository.CreditLimitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CreditLimitService {

    private final CreditLimitRepo creditLimitRepo;

    public Map<String, CreditLimit> getAll() {
        return creditLimitRepo.getAll();
    }

    public CreditLimit findByCardNumber(String cardNumber) {
        return creditLimitRepo.findById(cardNumber);
    }
}
