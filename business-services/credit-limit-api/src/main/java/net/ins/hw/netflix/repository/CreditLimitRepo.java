package net.ins.hw.netflix.repository;


import net.ins.hw.netflix.domain.Limit;
import net.ins.hw.netflix.domain.Limit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditLimitRepo extends MongoRepository<Limit, String> {
    Limit findByCardNumber(String cardNumber);
}
