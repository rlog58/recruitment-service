package org.recruitmentservice.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.recruitmentservice.dto.EmployerDto;
import org.recruitmentservice.entity.Employer;
import org.recruitmentservice.service.EmployerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class EmployerController {

    private final EmployerService employerService;

    private final ModelMapper modelMapper;

    @GetMapping("/employer")
    @ResponseStatus(HttpStatus.OK)
    public EmployerDto getEmployerById(@RequestHeader(value = "id") Long id) {
        Employer employer = employerService.findById(id);
        return convertToDto(employer);
    }

    private Employer convertToEntity(EmployerDto employerDto) {
        Employer employer = modelMapper.map(employerDto, Employer.class);
        return employer;
    }

    private EmployerDto convertToDto(Employer employer) {
        EmployerDto employerDto = modelMapper.map(employer, EmployerDto.class);
        return employerDto;
    }
}
