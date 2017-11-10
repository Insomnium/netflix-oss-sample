package com.epam.hw.netflix.services;

import com.epam.hw.netflix.domain.Employee;
import com.epam.hw.netflix.repository.EmployeesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeesRepo employeesRepo;

    public Employee findEmployee(String id) {
        return employeesRepo.findById(id);
    }
}
