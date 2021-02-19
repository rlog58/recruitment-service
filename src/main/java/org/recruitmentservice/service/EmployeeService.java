package org.recruitmentservice.service;


import lombok.RequiredArgsConstructor;
import org.recruitmentservice.entity.Employee;
import org.recruitmentservice.exception.NoSuchEmployeeException;
import org.recruitmentservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NoSuchEmployeeException(employeeId));
        return employee;
    }

}
