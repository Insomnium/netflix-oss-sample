package net.ins.hw.netflix.repository;


import lombok.RequiredArgsConstructor;
import net.ins.hw.netflix.domain.Limit;
import net.ins.hw.netflix.dummy.DummyLimits;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreditLimitRepo {

    private final DummyLimits dummySource;

    public Limit findByCardNumber(String cardNumber) {
        return dummySource.getLimits()
                .stream()
                .filter(limit -> limit.getCardNumber().equals(cardNumber))
                .findFirst()
                .orElse(null);
    }

    public Limit save(Limit limit) {
        dummySource.getLimits().add(limit);
        return limit;
    }

    public void deleteAll() {
        dummySource.getLimits().clear();
    }
}
