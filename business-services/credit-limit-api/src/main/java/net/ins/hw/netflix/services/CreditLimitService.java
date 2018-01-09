package net.ins.hw.netflix.services;

import net.ins.hw.netflix.domain.Amount;
import net.ins.hw.netflix.domain.CreditLimit;
import net.ins.hw.netflix.domain.CreditLimitStatus;
import net.ins.hw.netflix.domain.Limit;
import net.ins.hw.netflix.repository.CreditLimitRepo;
import lombok.RequiredArgsConstructor;
import net.ins.hw.netflix.domain.Amount;
import net.ins.hw.netflix.domain.CreditLimit;
import net.ins.hw.netflix.domain.CreditLimitStatus;
import net.ins.hw.netflix.domain.Limit;
import net.ins.hw.netflix.repository.CreditLimitRepo;
import org.springframework.stereotype.Service;

import static net.ins.hw.netflix.domain.CreditLimitStatus.APPROVED;
import static net.ins.hw.netflix.domain.CreditLimitStatus.EXCEEDED;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class CreditLimitService {

    private final CreditLimitRepo creditLimitRepo;

    public CreditLimit checkLimit(String cardNumber, Amount amount) {
        Limit limit = creditLimitRepo.findByCardNumber(cardNumber);
        return ofNullable(limit)
                .map(l -> checkLimit(l, amount))
                .orElse(new CreditLimit(CreditLimitStatus.APPROVED));
    }

    private CreditLimit checkLimit(Limit dbLimit, Amount amount) {
        Long maxAmount = dbLimit.getLimit();

        if (maxAmount != null && extractAmount(dbLimit.getCurrencyCode(), amount).compareTo(maxAmount) > 0) {
            return new CreditLimit(CreditLimitStatus.EXCEEDED);
        }

        return new CreditLimit((CreditLimitStatus.APPROVED));
    }

    private Long extractAmount(String currencyCode, Amount amount) {
        if (!amount.getCurrency().getCode().equals(currencyCode)) {
            throw new UnsupportedOperationException("TODO: convert(amount.value, currencyCode)");
        }
        return amount.getAmount();
    }
}
