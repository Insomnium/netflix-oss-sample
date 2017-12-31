package com.epam.hw.netflix.repository;


import com.epam.hw.netflix.domain.CreditLimit;

import java.util.List;
import java.util.Map;

public interface CreditLimitRepo {
    Map<String, CreditLimit> getAll();
    CreditLimit findById(String id);
}
