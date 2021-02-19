package org.recruitmentservice.controller;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.recruitmentservice.dto.EmployeeDto;
import org.recruitmentservice.entity.Employee;
import org.recruitmentservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    private final ModelMapper modelMapper;

    @GetMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getEmployeeById(@RequestHeader(value = "id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return convertToDto(employee);
    }

    private Employee convertToEntity(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return employee;
    }

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }
}
