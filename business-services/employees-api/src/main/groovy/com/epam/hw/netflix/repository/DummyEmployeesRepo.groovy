package com.epam.hw.netflix.repository

import com.epam.hw.netflix.domain.Employee
import com.epam.hw.netflix.exceptions.NoEmployeeFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DummyEmployeesRepo implements EmployeesRepo {

    @Autowired
    DummyEmployees employees

    @Override
    Employee findById(String id) {
        def empl = employees.dummy.find { it.id == id }
        if (!empl) throw new NoEmployeeFoundException("No employee found with id: $id")
        return empl
    }
}
