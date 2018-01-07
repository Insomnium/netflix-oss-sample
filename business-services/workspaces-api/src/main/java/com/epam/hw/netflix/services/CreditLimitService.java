package com.epam.hw.netflix.services;

import com.epam.hw.netflix.domain.Amount;
import com.epam.hw.netflix.domain.CreditLimit;
import com.epam.hw.netflix.domain.CreditLimitStatus;
import com.epam.hw.netflix.domain.Limit;
import com.epam.hw.netflix.repository.CreditLimitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.epam.hw.netflix.domain.CreditLimitStatus.APPROVED;
import static com.epam.hw.netflix.domain.CreditLimitStatus.EXCEEDED;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class CreditLimitService {

    private final CreditLimitRepo creditLimitRepo;

    public CreditLimit checkLimit(String cardNumber, Amount amount) {
        Limit limit = creditLimitRepo.findByCardNumber(cardNumber);
        return ofNullable(limit)
                .map(l -> checkLimit(l, amount))
                .orElse(new CreditLimit(APPROVED));
    }

    private CreditLimit checkLimit(Limit dbLimit, Amount amount) {
        Long maxAmount = dbLimit.getLimit();

        if (maxAmount != null && amount.getAmount().compareTo(extractAmount(dbLimit.getCurrencyCode(), amount)) > 0) {
            return new CreditLimit(EXCEEDED);
        }

        return new CreditLimit((APPROVED));
    }

    private Long extractAmount(String currencyCode, Amount amount) {
        // TODO: if currencyCode != amount.currencyCode -> convert(amount.value, currencyCode) else amount.value
        return null;
    }
}
