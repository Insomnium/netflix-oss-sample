package com.epam.hw.netflix.repository;


import com.epam.hw.netflix.domain.Limit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditLimitRepo extends MongoRepository<Limit, String> {
    Limit findByCardNumber(String cardNumber);
}
