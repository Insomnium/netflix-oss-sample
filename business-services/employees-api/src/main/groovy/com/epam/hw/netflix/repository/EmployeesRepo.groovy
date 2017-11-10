package com.epam.hw.netflix.repository

import com.epam.hw.netflix.domain.Employee


interface EmployeesRepo {
    Employee findById(String id)
}